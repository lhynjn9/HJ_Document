package com.hj.aop;

import java.util.Random;

public class PersonProxy implements Person{
	private Person person;
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		// 프록시에 넣어서 대신 부르기
		// 공통적인 부분의 처리
		System.out.println("입실 체크를 합니다.");
		
		try {
			person.doSomething();
			if(new Random().nextBoolean())
				throw new CoronaException();
			System.out.println("퇴실 체크를 합니다.(6시 정시");
		}
		
		catch(CoronaException e) {
			System.out.println("코로나 발생으로 인한 임의 토실");
		}
		
		finally {
			System.out.println("꿀같은 저녁시간");
		}
		
	}
}
