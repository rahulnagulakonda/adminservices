package com.interon.admin.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.interon.admin.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	private static String secret = "This_is_secret";
	private static long expiryDuration = 60 * 60;

	public String generateJwt(User user) {
		
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;

		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims
		Claims claims = Jwts.claims()
				.setIssuer(user.getEmail())
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		
		claims.put("role", user.getRoles());

		// generate jwt using claims
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public Claims verifyJwt(String authorization) {

		try {
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
			System.out.println(claims);
			return claims;
		} catch (RuntimeException re) {
			throw new RuntimeException("Access Denied");
		}
	}
}
