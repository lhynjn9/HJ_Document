package com.hj.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

// CEO가 Computer 생성 
// CEO가 Worker를 생성하며 Computer를 전달
// CEO가 Worker의 work 수행


// 목표 : 인터페이스를 이용해서, 타입의존성을 느슨하게 만드는 것
// 공통 역할(getInfo)을 인터페이스로 정의

//worker가 자신이 사용할 computer를 직접 생성하지 않고, CEO가 대신 생성해서 넣어줌
//CEO가 의존성을 갖게 됨 -> Worker의 의존성이 CEO로 감 : IoC(의존성 역전)
//나(Worker)를 사용하는 누군가(CEO)가 대신 computer를 전달해줌)
//만들어진 것을 받는 구조 : 제어의 역전(public Worker(Computer computer))
//의존성 주임 : 생성자를 통한 방법, 설정자를 통한 방법
public class CEO{
	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		// id를 지정하지 않으면 클래스명을 소문자로 바꾼 것이 id가 됨
		// @Component("computer") 로 직접 지정가능
		Computer computer = context.getBean("desktop", Desktop.class);
//		Computer computer = context.getBean("computer", Desktop.class);
		System.out.println(computer.getInfo());
		
		// Annotation 방식으로 만들때는 기본 생성자가 없지만, 생성자가 하나뿐이면서 들어갈 매개변수가 빈에 있으면 자동으로 생성
		// 만약 들어갈 매개변수가 2개(Labtop, Desktop 모두 Component)일 경우 에러 ->  선택지 지정 필요
		// 1. @Qualifier로 알려줌
			// public Worker(@Qualifier("desktop") Computer computer) {
			// this.computer = computer;
			// }
		// 2. bean의 이름@Component("computer")이 내가 찾는 것과 같으면 그것을 가져옴 
			// public Worker(Computer computer) {
			// this.computer = computer;
			// }
		Worker worker = context.getBean("worker", Worker.class);
		worker.work();
	}
}