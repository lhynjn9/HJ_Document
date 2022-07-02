package com.ss.hj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ss.hj.dto.User;

// 이 클래스가 컨트롤러 임을 어노테이션으로 설정, 컴포넌트 스캔을 통해 빈으로 등록
@Controller
public class UserController {
	
	/**
	 * '/' 또는 '/index' 요청이 get 방식으로 들어왔을 때 index로 연결한다.
	 */
	@GetMapping({"/","/index" })
	public String showIndex() {
		return "index";
	}
	
	/**
	 * '/regist' 요청이 get 방식으로 들어왔을 때 regist로 연결한다.
	 */
	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}

	/**
	 * '/regist' 요청이 post 방식으로 들어왔을 때 전달된 User 객체를 regist_result로 연결한다. 
	 */
	@PostMapping("/regist")
	public ModelAndView doRegist(User user) {
		ModelAndView mav = new ModelAndView();
		// 어디로 연결할지 설정
		mav.setViewName("regist_result");
		// 전달할 객체
		mav.addObject("user", user);
		return mav;
	}
}