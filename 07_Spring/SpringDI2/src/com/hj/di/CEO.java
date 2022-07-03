package com.hj.di;

// CEO가 Worker를 생성
// CEO가 Worker를 생성하며, computer를 전달
// CEO가 Worker의 work 수행


// 목표 : 인터페이스를 이용해서, 타입의존성을 느슨하게 만드는 것
// 공통 역할(getInfo)을 인터페이스로 정의

public class CEO{
	public static void main(String[] args) {
//		Computer computer = new Labtop();
		Worker worker = new Worker();
		worker.work();
	}
}