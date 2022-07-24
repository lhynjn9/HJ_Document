package com.hj.board.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	private static final String SALT = "HYEON";
	
	// 토큰 생성(Create)
	// 헤더 정보
	// payload
	// 서명
	// compact 해서 문자열로 만들어 줌
	public String createToken(String claimId, String data) throws UnsupportedEncodingException {
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim(claimId, data)
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.compact();
//				.setExpiration(new Date(System.currentTimeMillis()+3000)) // 유효시간 등록 가능 : 3초
	}
	
	
	// 토큰 검증
	public void checkToken(String token) throws Exception {
		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
	}

}
