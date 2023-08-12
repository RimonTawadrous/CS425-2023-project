package registrationsystem.api.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig
{

    private final UserDetailsService userDetailsService;
    private final AuthTokenFilter authTokenFilter;
    private final PasswordEncoder passwordEncoder;



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean // (3)
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }


    @Bean // (4)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                // @formatter:off
                http.csrf(AbstractHttpConfigurer::disable)
                // Add token filter
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())

                // Set unauthorized requests exception handler
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(new HandlerAuthenticationEntryPoint());
                    exception.accessDeniedHandler(new HandlerAccessDeniedHandler());
                    }
                ).sessionManagement(sessionManagement ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                // Set permissions on endpoints
                .authorizeHttpRequests((request) ->{
                    request.requestMatchers("/api/v1/auth/**").permitAll();
                    request.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    request.requestMatchers(
                            "/configuration/ui",
                            "/swagger-resources/**",
                            "/configuration/security",
                            "/webjars/**").permitAll();
                    request.requestMatchers("/api/v1/**").authenticated();

                        }

                );


        // @formatter:on
        return http.build();
    }


}
