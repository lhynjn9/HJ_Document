package com.hj.prespringmvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// filter는 Filter(javax.servlet)를 상속받아야 함
public class CharacterEncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	// 어떤 요청이 서블릿으로 가기전에 수행해야 될 작업
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("이 요청은 인코딩 세팅 해드렸습니다.");
		// 서블릿 전 작업
		chain.doFilter(request, response);
		System.out.println("어때 내가 인코딩해주니 편하지?");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
