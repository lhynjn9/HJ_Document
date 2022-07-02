package com.hj.di;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer{
	public String getInfo() {
		return "테스크탑";
	}
}
