package com.hj.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController{
	
	// localhost:8080/board
	@RequestMapping("/")
	public String home() {
		// /WEB-INF/views/home.jsp로 forward
		return "home";
	}
	
}