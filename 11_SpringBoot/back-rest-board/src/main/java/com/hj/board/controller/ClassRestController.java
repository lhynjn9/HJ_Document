package com.hj.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hj.board.model.dto.User;
import com.hj.board.util.JWTUtil;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class ClassRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		HttpStatus status = null;
		
		HashMap<String, Object> result = new HashMap<>();
		
		//user 실제 DB에서 검증
		//UserSerivce를 불러 UserDao를 불러서 DB통신하여 결과를 얻어와야 함
		//if 존재한다면 토큰을 생성해서 결과에 넣어서 반환하여라
		try{
			if(user.getId() != null || user.getId().length() > 0) { // 검증 단계 일단 생략
				result.put("access-token", jwtUtil.createToken("id", user.getId())); // id정보만 담겨 있는 토큰
				result.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}else {
				//실패 시
			}
			
		} catch(Exception e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
}
