package com.hj.aop;

import java.util.Random;

public class HJ {
	
	public void doSomething() {
		System.out.println("입실 체크를 합니다.");
		
		try {
			System.out.println("소와 같이 공부 함");
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
