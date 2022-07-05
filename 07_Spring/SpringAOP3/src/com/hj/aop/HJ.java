package com.hj.aop;

import java.util.Random;

public class HJ implements Person{

	@Override
	public void doSomething() throws CoronaException {
		// TODO Auto-generated method stub
		System.out.println("소와 같이 공부를 함");
		if(new Random().nextBoolean())
			throw new CoronaException();
	}
	
}