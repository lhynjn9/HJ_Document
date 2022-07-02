package com.hj.di;

// CEO가 Worker를 생성
// CEO가 Computer 생성 
// CEO가 Worker에게 Computer를 줌
// CEO가 Worker의 work 수행


// 목표 : 인터페이스를 이용해서, 타입의존성을 느슨하게 만드는 것
// 공통 역할(getInfo)을 인터페이스로 정의

// worker가 자신이 사용할 computer를 직접 생성하지 않고, CEO가 대신 생성해서 넣어줌
// CEO가 의존성을 갖게 됨 -> Worker의 의존성이 CEO로 감 : IoC(의존성 역전) 
// 만들어진 것을 받는 구조 : 제어의 역전(public Worker(Computer computer))
// 의존성 주임 : 생성자를 통한 방법, 설정자를 통한 방법

// 하나의 공간에서만 new가 발생한다면 관리가 쉬워짐 -> 팩토리 패턴
public class CEO{
	public static void main(String[] args) {
		Worker worker = new Worker();
		Computer computer = new Labtop();
		// 설정자 : 프로그램 중간에 의존관계를 바꿀 수 있음
		// 주입을 하지 않아도 worker는 생성가능
		// 추후 주입한 것을 변경 가능
		// 필요에 따라 구현체를 바꾸서 생성 -> 전략 패턴
		worker.setComputer(computer);
		worker.work();
	}
}