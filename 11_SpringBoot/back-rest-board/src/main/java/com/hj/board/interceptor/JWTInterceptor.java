package com.hj.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hj.board.util.JWTUtil;

@Component
public class JWTInterceptor implements HandlerInterceptor{
	
	private static final String HEADER_AUTH = "access-token";
	
	@Autowired
	private JWTUtil jwtUtil;
	
	// 요청이 오면 처리하기 전 확인 : preHande
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		
		final String token = request.getHeader(HEADER_AUTH);
		
		if(token != null) {
			jwtUtil.checkToken(token);
			return true;
		}
		throw new Exception("유효하지 않은 접근입니다.");
	}
}
