package com.example.parkingsystem.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;



import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    private final Key key;

    public JWTUtil() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
  
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return parseToken(token).getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid or expired JWT token", e);
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Authentication getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
        String role = claims.get("role", String.class);

        return new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                role != null ? 
                    List.of(new SimpleGrantedAuthority(role)) : 
                    List.of()
        );
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
