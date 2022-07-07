package com.hj.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


// 하나의 클래스에 뷰와 rest를 제공하고 싶다면
// RestController = Controller + ResponseBody
@RestController
@RequestMapping("/rest2")
public class TestController2 {
	// http://localhost:8080/rest1/test1 : 페이지를 찾지 못함
	@GetMapping("/test1")
	public String test1() {
		return "hi rest";		
	}
	
	// http://localhost:8080/rest1/test2
	@GetMapping("/test2")
	public String test2() {
		return "hi rest";		
	}
	
	// http://localhost:8080/rest1/test2
	// 객체를 보내면 json 형태로 전송
	// 스프링이 변환시켜서 전송
		@GetMapping("/test3")
		public Map<String, String> test3() {
			Map<String, String> data = new HashMap<>();
			data.put("id", "sbs");
			data.put("name", "이현정");
			data.put("email", "naver");
			return data;	
		}
		
		
		// dto 객체
		@GetMapping("/test4")
		public Member test4() {
			Member m = new Member();
			
			m.setId("dl");
			m.setPassword("dfd");
			m.setEmail("naver");
			
			return m;
		}
		
		@GetMapping("/test5")
		public List<Member> test5() {
			List<Member> list = new ArrayList<>();
			for(int i = 1; i < 4; i++) {
				Member m = new Member();
				m.setId("sbs" + i);
				m.setPassword("이현정" + i);
				m.setEmail("email" + i);
				list.add(m);
			}
			return list;
			}
}
