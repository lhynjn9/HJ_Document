package com.hj.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Test {
	public static void main(String[] args) {
		ApplicationContext context 
		= new GenericXmlApplicationContext("applicatonContext.xml");
		
		Person hj = context.getBean("hj", Person.class);
		try {
			hj.doSomething();
		} catch (CoronaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
