package com.hms.user.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final Long JWT_TOKEN_VALIDITY = 5*60*60L;//in millisecond, for secong = *1000
    private static final String SECRET = "6d089bd7d9a07ef33067c58488e2ab9f0aa59a8b712ae5fb0d23d370aa71fb8a895e8cf423598a707a7107d29a58afaf157ab224108891a5a21d9ffa761f1182";

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        CustomUserDetails user = (CustomUserDetails)userDetails;
    }

    public String doGenerateToken(Map<String,Object> claims, String subject){//subject = email
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis())+JWT_TOKEN_VALIDITY*1000)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

}
