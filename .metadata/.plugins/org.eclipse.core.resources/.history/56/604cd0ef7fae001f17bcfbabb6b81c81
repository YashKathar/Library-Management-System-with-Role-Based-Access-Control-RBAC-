package com.librarymanagement.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;

@Service
public class JwtService {
	
	@Value("${app.jwt.secret_key}")
	private String SECRET_KEY;
	
	
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<String, Object>();
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
				.signWith(getKey())
				.compact();
	}



	private Key getKey() {
		// TODO Auto-generated method stub
		byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decode);
	}
	
	
}
