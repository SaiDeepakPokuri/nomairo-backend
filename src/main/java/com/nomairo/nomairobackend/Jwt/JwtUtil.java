package com.nomairo.nomairobackend.Jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import com.nomairo.nomairobackend.Domain.MyUser;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your-256-bit-secret-key-here-make-it-very-long-and-secure";

    public String generateToken(MyUser user) {

        String jwtToken=null;

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        jwtToken = Jwts.builder()
        .subject(user.getId().toString())
        .issuedAt(new Date())
        .signWith(key)
        .compact();

        return jwtToken;
    }

    public Claims validaateToken(String token){
        try{
     
        
         Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
     
         return Jwts.parser()
         .verifyWith((SecretKey) key)
         .build()
         .parseSignedClaims(token)
         .getPayload();
         }catch(Exception e){
             throw new RuntimeException("Invalid token");
         }
         }
}
