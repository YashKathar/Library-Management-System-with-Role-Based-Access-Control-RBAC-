package com.librarymanagement.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
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



	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims::getSubject);
		
	}



	private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
		// TODO Auto-generated method stub
		final Claims claim = extractAllClaims(token);
		return claimResolver.apply(claim);
	}



	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}



	public boolean validateToken(String token, UserDetails userDetails) {
		String extractUserName = extractUserName(token);
		return (extractUserName.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}



	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpirationToken(token).before(new Date());
	}



	private Date extractExpirationToken(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims::getExpiration);
	}
	
	
}
