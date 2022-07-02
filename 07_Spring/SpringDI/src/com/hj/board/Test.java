package com.hj.board;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hj.board.model.dto.Board;
import com.hj.board.model.service.BoardService;

public class Test {
	public static void main(String[] args) {
		// applicatoinContext 빌드 : 객체 공장
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 필요한 객체 회수
		BoardService boardService = context.getBean("boardServiceImpl", BoardService.class);
		
		// 사용 : dao 신경안 쓰고 service만 호출
//		boardService.readBoard(1);
		
		for(Board b : boardService.getBoardList()) {
			System.out.println(b);
		}
	}
}
