package com.github.cainamott.nery_farma.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //@Value("${JWT.SECRET}")
    private String secret;

    //@Value("${JWT.EXPIRATION}")
    private long expiration;


    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        String profile  = userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse("USER");

        claims.put("profile", profile);
        return buildToken(claims, userDetails);
    }


    private String buildToken(Map<String, Object> extraClaims, UserDetails details){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(details.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails details){
        final String username = extractUsername(token);
        return username.equals(details.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolveClaim){
        final Claims claims = extractAllClaims(token);
        return resolveClaim.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] bytes = secret.getBytes();
        return Keys.hmacShaKeyFor(bytes);
    }
}
