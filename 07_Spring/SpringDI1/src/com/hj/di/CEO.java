package com.hj.di;

// CEO가 Worker를 생성
// Worker가 Computer 생성
// CEO가 Worker의 work 수행

// 타입으로 선언할 때와 객체를 생성할때 의존성이 발생
public class CEO{
	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.work();
	}
}