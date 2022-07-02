package com.hj.di;

public class Desktop implements Computer{
	public Desktop() {
		System.out.println("Desktop 생성");
	}
	public String getInfo() {
		return "테스크탑";
	}
}
