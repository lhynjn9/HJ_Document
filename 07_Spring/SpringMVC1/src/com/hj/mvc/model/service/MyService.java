package com.hj.mvc.model.service;

import org.springframework.stereotype.Service;

// 설정파일(applicationContext)와 web.xml의 listener에 의해서 빈으로 등록됨
@Service
public class MyService{
	public void doSomething() {
		System.out.println("안녕하세요, MyService 입니다.");
	}
	
}