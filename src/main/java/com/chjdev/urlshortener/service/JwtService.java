package com.chjdev.urlshortener.service;

import com.chjdev.urlshortener.entity.UserEntity;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private  String secret;



    public String generateToken(UserEntity user){

        return Jwts.builder().subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofHours(1))))
                .signWith(getSigningKey())
                .compact();

    }

    private Key getSigningKey(){

        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    }




}
