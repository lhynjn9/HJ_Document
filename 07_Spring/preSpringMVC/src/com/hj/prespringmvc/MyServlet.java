package com.hj.prespringmvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet 만들 때, HttpServlet을 상속 받음
public class MyServlet extends HttpServlet{
// servlet과 jsp는 요청에 대한 처리 담당자
// 1. servlet @WebServlet을 이용해서
// 처리할 요청을 지정해줌으로써 요청에 대한 처리 담당자로 등록
// 2. web.xml를 이용해서도 처리 담당자로 등록 가능	

	// Source-Override로 생성
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		// 실행 후, url에 /hello 를 입력하면 출력이 나오면서 이곳이 실행된 것을 확인 가능
		System.out.println(req.getRequestURL());
	}

}
