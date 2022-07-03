package com.hj.aop;

public class Test{
	// 입실체크 Before
	// 소와 같이 공부
	// 코로나 발생 : 임의 퇴실 after-throwing
	// 퇴실체크(6시 정시) after-returning
	// 달달한 저녁시간을 보냄 after

	
	// 입실체크
	// 소와 같이 일
	// 코로나 발생 : 임의 퇴실
	// 퇴실체크(6시 정시)
	// 달달한 저녁시간을 보냄

	// 공통 관심 사항 : 입실 체크, 퇴실 체크, 저녁시간
	// 핵심 관심 사항 : 공부와 일
	// 예외 발생 : 코로나 발생
	
	// 핵심 관심 사항 이전의 일: Before
	// 핵심 관심사항 중에 일어난 예외 : after-throwing
	// 핵심 관심사항이 예외외없이 끝났을 때 : after-returning
	// 예외 발생 여부에 따라 after-throwing과 after-returning이 선택적으로 발생
	// 핵심관심 사항이 끝났을 때 : after
	
	public static void main(String[] args) {
		
	}
}