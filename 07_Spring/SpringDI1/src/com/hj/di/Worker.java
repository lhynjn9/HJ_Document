package com.hj.di;

// 다른 클래스의 객체를 사용하거나 소유할 때, 의존성 생성
// Labtop을 Desktop으로 변경할때 의존성 발생 부분을 모두 수정해주어야함
public class Worker {
	// 의존성 발생 1 : 타입 선언할 때
	private Labtop computer;
	
	// 생성자 : computer 객체를 만들어 이용하기 위힘
	public Worker() {
		// 의존성 발생 2 : 객체를 생성할 때
		computer = new Labtop();
	}
	
	public void work() {
		System.out.println(computer.getInfo() + "로 소와 같이 일함");
	}
}
