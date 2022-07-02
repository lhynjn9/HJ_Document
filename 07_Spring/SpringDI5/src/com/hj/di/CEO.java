package com.hj.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// Spring Container : 객체 관리 컨테이너
// 스프링 설정 파일 작성 후, ApplicationContext 빌드
public class CEO{
	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		System.out.println("컨테이너 만들어짐");
		// 컨터이너로부터 내가 사용할 객체를 받아옴
		// getBean의 반환유형 : Object -> 형변환 필요
		
		// Worker로 형변환
		Worker worker = (Worker) context.getBean("worker");
		// Desktop.class로 형변환
		Computer computer = context.getBean("computer", Desktop.class);
		worker.setComputer(computer);
		worker.work();
		
		// 필요에 따라 여러번 받아오기 가능
		Computer computer2 = context.getBean("computer", Desktop.class);
		Computer computer3 = context.getBean("computer", Desktop.class);
		
		// object에 의해서 자기 자신의 주소 출력
		// 모두 동일한 주소 출력
		// Spring Container는 객체를 하나만 만들어, 갖고 있던 것을 계속 줌
		
		// 객체가 만들어지는 시점
		// 1. 컨터이너가 만들어질 때/빌드할 때, 필요한 Bean 객체를 생성함
		// 2. lazy-init속성을 true로 주면, 최초로 getBean이 호출되어 질때, 해당 객체를 생성
		// 3. scope가 prototype이면 getBean을 할때마다 만들어냄
		System.out.println(computer);
		System.out.println(computer2);
		System.out.println(computer3);
	}
}

