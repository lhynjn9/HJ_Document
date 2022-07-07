package com.hj.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	// 클라이언트가 보내준 것이 json형태일 때 알려주는 것 @RequsetBody
	@PostMapping("/test2")
	public String test2(@RequestBody Member m) {
		return String.format("id: %s, email: %s", m.getId(), m.getEmail());
		
	}
	
	// ResponseEntity : 응답하려는 데이터(Body) + 응답 헤더(헤더) + 응답코드(시작 라인) 조작 가능
	// 자체로 ResponseBody를 포함
	// ResponseEntity<T> : T <- Body에 들어갈 데이터의 타입
	@GetMapping("/test3")
	public ResponseEntity<String> test3(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("auth", "12341234");
		return new ResponseEntity<String>("OK 성공", headers, HttpStatus.OK);
		
	}
	 
}
