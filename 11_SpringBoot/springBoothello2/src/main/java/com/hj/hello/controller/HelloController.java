package com.hj.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 1. 컨트롤러 등록
@Controller
public class HelloController {
	// 2. 메소드 생성
	@GetMapping("/") // 4. 어떤 요청과 매핑되는지 작성
	public String hello() {
		// 3. 넘어갈 뷰페이지 정보 전달
		return "hello";
	// /WEB-INF/views/hello.jsp로 동작
	}
}
