package registrationsystem.api.security;

import java.io.IOException;
import java.util.Optional;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import registrationsystem.api.model.User;
import registrationsystem.api.service.UserService;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtTokenService tokenProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        Optional<String> jwt = resolveHeaderToken(request);

        if (jwt.isPresent() && tokenProvider.validateToken(jwt.get())) {
            String username = tokenProvider.extractUserName(jwt.get());
            if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                User userDetails = (User) userService.loadUserByUsername(username);
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> resolveHeaderToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ") ? Optional.of(bearerToken.substring(7)) : Optional.empty();
    }
}
