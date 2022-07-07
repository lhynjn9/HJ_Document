package com.hj.rest.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // setter, getter 한번에 생성
@NoArgsConstructor // 매개변수가 없는 생성자 생성
@AllArgsConstructor // 모든 멤버 변수로 생성자를 생성
public class Member {
	private String id;
	private String password;
	private String email;

}   