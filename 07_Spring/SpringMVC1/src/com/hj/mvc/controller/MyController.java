package com.hj.mvc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;

import com.hj.mvc.model.dto.User;
import com.hj.mvc.model.service.MyService;

@Controller
public class MyController {
	// Controller가 Service class를 파라미터로 갖고 있다면
	// 컴포넌트 스캔에 의해서 컨트롤러가 빈으로 등록 되므로
	private MyService myService;
	
	// ContextLoaderListener를 listner로 등록을 하면,
	// 해당 스프링 설정파일(root-context.xml)을 매개로 삼아서 root container를 빌드하게 해줌
	
	// @Autowired를 넣어주기 위해서는 Service가 빈으로 등록되어 있어야함
	@Autowired
	public void setMyService(MyService myService) {
		this.myService = myService;
	}
	
	// 실제로 처리할 함수들 하나하나
	// 호출할  함수의 구분은 handler mapping
	// 1. annotation 기반 : default로 존재
	@RequestMapping("home") //8080/SpringMVC1/home
	public ModelAndView handler1() {
		// data와 jsp 페이지 명을 지정할 수 있음
		// ModelAndView 객체 생성
		ModelAndView mav = new ModelAndView();
		// 동작 확인(root container)
		myService.doSomething();
		// req.setAttribute의 역할
		mav.addObject("msg", "Hello World Welcome to Hell");
		// req.getRequestDispatcher("home.jsp")의 역할
//		mav.setViewName("/WEB-INF/view/home.jsp");
		// view name지정으로 인한 변화
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("whattime")//8080/SpringMVC1/whattime
	public ModelAndView handler2() {
		// ModelAndView 객체 생성
				ModelAndView mav = new ModelAndView();
				// req.setAttribute의 역할
				mav.addObject("time", new Date());
				// req.getRequestDispatcher("home.jsp")의 역할
//				mav.setViewName("/WEB-INF/view/whattime.jsp");
				// view name지정으로 인한 변화
				mav.setViewName("whattime");
				return mav;
		
	}
	
	// return 하는 값이 viewname이다
	@GetMapping("test1")
	public String test1() {
		return "test1";
	}
	
	
	// return 타입이 String일 때, attribute를 가져가는 방법
	// 필요한 것이 있으면 요청해서 받아라
	@GetMapping("test2")
	public String test2(Model model) {
		model.addAttribute("msg", "준비해 주신 model에 싣는다");
		return "test2";
	}
	
	// parameter를 받고 싶으면 요청해서 받아라
	// 변수명이 파라미터의 키 값이 됩니다
	// 기본적으로는 해당 파라미터가 들어오지 않으면, 해당 변수는 null
	// 이름이 다를 경우, @RequestParam을 통해 맞춰줄 수 있음
	// http://localhost:8080/SpringMVC1/test3?id=hong&pw=new
	// 파라미터 타입을 지정해두면 형변환까지 해줌
	// http://localhost:8080/SpringMVC1/test3?id=hong&pw=hong&age=27
	@GetMapping("test3")
	public String test3(Model model, String id, String pw, int age) {
		model.addAttribute("myid", id);
		model.addAttribute("mypw", pw);
		System.out.println(id+ " " +pw+ " " + (age/10*10));
		return "test3";
	}
	
	// myid로 들어오는 값을 id에 연결
	// @RequestParam 옵션 : value : 해당 값을 변수에 넣어줌
	// required=true : 해당 파라미터가 오지 않으면 요청 처리 불가
	// defaultValue="hong" : 해당 파라미터가 오지 않으면 hong으로 값이 주어짐
	// http://localhost:8080/SpringMVC1/test3_1?myid=hong&pw=new
	@GetMapping("test3_1")
	public String test3_1(Model model, @RequestParam(value="myid", required=true, defaultValue="hong2") String id, String pw) {
		model.addAttribute("myid", id);
		model.addAttribute("mypw", pw);
		System.out.println(id+ " " +pw);
		return "test3_1";
	}
	
	
	@PostMapping("test4")
	public String test4(Model model, User user) {
		System.out.println(user);
		// 할일 하고(=Service의 영역)
		// model.addAttribute(데이터)
		return "test4";
	}
}
