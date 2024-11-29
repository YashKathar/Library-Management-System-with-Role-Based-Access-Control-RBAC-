//package com.librarymanagement.util;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.librarymanagement.enums.Role;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtUtil {
//	@Value("${app.jwt.secret_key}")
//	private String SECRET_KEY;
//	
//	public String generateToken(String userName, Role role) {
//		return Jwts.builder()
//				.setSubject(userName)
//				.claim("role", role)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60))
//				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//				.compact();
//	}
//	
//	public Claims extractClaims(String token) {
//		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//	}
//}
