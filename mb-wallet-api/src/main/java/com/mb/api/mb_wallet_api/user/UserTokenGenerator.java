package com.mb.api.mb_wallet_api.user;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class UserTokenGenerator {
	// We need a signing key, so we'll create one. Usually
	// the key would be read from application configuration instead.
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//	private static final int EXPIRY_DAYS = 90;
	public String generateUserToken(String userId) {
//		//System.out.println("Inside User Token generator!");

		String jws = Jwts.builder().setSubject(userId).signWith(key).compact();
//		//System.out.println(" jws:" + jws);
		return jws;
	}

	public String parseUserIdFromInputToken(String token) {

		String userId = null;
		try {
			//System.out.println("key:" + key);
			userId = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();

			//System.out.println(					Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject());

		} catch (JwtException e) {
			//System.out.println("Hack World! jws:" + e.getMessage());

		}

		return userId;
	}
}
