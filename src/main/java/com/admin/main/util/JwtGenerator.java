package com.admin.main.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.admin.main.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	private static String secret = "This_is_secret";
	private static long expiryDuration = 60 * 60;

	public String generateJwt(User user) {
		
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;

		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims
		Claims claims = Jwts.claims()
				.setIssuer(Integer.toString(user.getUserId()))
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);

		// generate jwt using claims
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
}
