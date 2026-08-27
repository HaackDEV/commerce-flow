package com.haackdev.commercial_management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value( "${jwt.secret}")
    private String secretKey;

    @Value( "${jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String email) {

        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                            .subject(email)
                            .issuedAt(now)
                            .expiration(expiry)
                            .signWith(getSigningKey())
                            .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }

    public boolean isTokenExpired(String token){
        try {
            Date expiration = extractClaim(token, Claims::getExpiration);
            return expiration.before(new Date());
        }
        catch (Exception e) {
            return true;
        }
    }

    public boolean isValidToken(String token, String email) {
        try {
            String emailFromToken = extractEmail(token);
            return email.equals(emailFromToken) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
