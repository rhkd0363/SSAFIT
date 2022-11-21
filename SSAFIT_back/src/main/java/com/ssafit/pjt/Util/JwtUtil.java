package com.ssafit.pjt.Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private static final String SALT = "SSAFIT";
	
	// 토큰 생성
	public String createToken(String claimId,String data) throws UnsupportedEncodingException {
		return Jwts.builder()
				.setHeaderParam("arg","HS256")
				.setHeaderParam("typ", "JWT")
				.claim(claimId, data)
//				.setExpiration(new Date(System.currentTimeMillis()+60*1000*60)) // 토큰 유지시간
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.compact();
	}
	
	//유효성 검증
	public void valid(String token) throws IOException{
		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
	}
	
	
}
