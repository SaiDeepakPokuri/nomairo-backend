package com.nomairo.nomairobackend.Jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String token=request.getHeader("Authorization");

                if(token==null||!token.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
              }

              token=token.substring(7);

              try{

                Claims claims=jwtUtil.validaateToken(token);

              String  userId=claims.getSubject();

                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userId, null,null);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

              }
              catch(Exception e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                
              }

              filterChain.doFilter(request, response);
       
       
    }

   

   
    
}
