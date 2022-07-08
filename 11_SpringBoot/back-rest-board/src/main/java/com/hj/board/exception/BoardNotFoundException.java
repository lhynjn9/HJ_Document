// 12.

package com.hj.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 이러한 상황일 때, 여기로 이동
public class BoardNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BoardNotFoundException(String msg) {
		super(msg);
	}
}
