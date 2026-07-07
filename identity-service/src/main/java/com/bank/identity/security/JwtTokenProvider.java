package com.bank.identity.security;

import com.bank.dto.RoleDto;
import com.bank.dto.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret));
    }

    public String generateToken(UserDto user) {
        List<String> roles = user.getAuthorities()
                .stream()
                .map(RoleDto::getRoleName)
                .toList();
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getUserId());
        claims.put("role", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()+expiration))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims  validate(String token){
        try{
            return getClaims(token);
        }catch(Exception e){
            return null;
        }
    }

}