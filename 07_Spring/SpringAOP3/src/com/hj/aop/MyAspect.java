package com.hj.aop;


// 프레임의 조각

public class MyAspect {
	public void before() {
		System.out.println("입실체크를 함");
	}
	
	public void after_rt() {
		System.out.println("퇴실 체크를 함");
	}
	
	public void after_th() {
		System.out.println("임의 퇴실");
	}
	
	public void after() {
		System.out.println("달달한 저녁시간");
	}
}
