package registrationsystem.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import registrationsystem.api.model.User;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    public static final String SECRET_KEY = "nUz6Xa4gewX07SQm7bCigUmfQ0KkeuJV1234567890agdfh";
    private final long ACCESS_TOKEN_EXPIRATION = 12900000; // temp

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(Instant.now());
    }

    public boolean validateToken(String token) {
        final String userName = extractUserName(token);
        return !isTokenExpired(token);
    }

    private Instant extractExpiration(String token) {
        return  extractClaim(token, Claims::getExpiration).toInstant();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(Map<String, Object> extraClaims, User user) {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    private String getSigningKey() {
        return SECRET_KEY;
    }

}
