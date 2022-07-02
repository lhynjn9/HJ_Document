package com.hj.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Worker {
	// 의존성 발생 1 : 타입 선언할 때
	// 타입을 추상적인 Computer에 의존하게 변경 => 타입 의존성이 느슨해짐 : 인터페이스를 활용한 느슨한 결합(loose coupling)
	// 객체 생성 부분만 수정하면 됨
	private Computer computer;
	
	// 생성자 : computer 객체를 만들어 이용하기 위함
	// 만들어진 Computer 구현 객체를 받아서 저장 -> 만들어진것을 받는 것 : 제어의 역전
//	public Worker(Computer computer) {
//		// 의존성 발생 2 : 객체를 생성할 때
//		// computer = new Desktop();
//		this.computer = computer;
//	}
	
	// 설정자를 통해 주입
	// Desktop과 Labtop 둘중 하나를 computer라는 이름으로 component하거나
	// @Qualified로 누군지 밝혀줘야 함
	// 타입이 맞는 것이 없으면 error
	// 타입이 맞는 것이 하나 있으면 성공
	// 타입이 맞는 것이 둘 이상이면, 이름이 맞는애가 있는 경우만 성공 아니면 error
	@Autowired
	@Qualifier("desktop")
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public void work() {
		System.out.println(computer.getInfo() + "로 소와 같이 일함");
	}
}
