package com.bridgelabz.fundoo.utility;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtGenerator {
	private static final String SECRET = "qwjsfhggfg";

	public String jwtToken(long l) {
		String token = null;
		try {
			token = JWT.create().withClaim("email", l).sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET));

		} catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

	public long parse(String string)
			throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		Long userId = 0l;
		if (string != null) {
			userId = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(string).getClaim("Id").asLong();
		}
		return userId;
	}
}
