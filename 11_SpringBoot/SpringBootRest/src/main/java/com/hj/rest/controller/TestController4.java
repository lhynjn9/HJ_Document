package com.hj.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest4")
public class TestController4 {

	@GetMapping("/board/{id}")
	// 파라미터가 아닌 path의 일부로 들어온 id를 받아야 함
	// 변수 이름이 다르다면 직접 매핑해줘야 함
	public String test1(@PathVariable("id") int no) {
	// public String test1(@PathVariable int id) {
		
		return "PathVariable" + no;
	}
	
	
	@PostMapping("/test2")
	public String test2(Member m) {
		return String.format("id: %s, name: %s", m.getId(), m.getEmail());
		
		
	}
}
