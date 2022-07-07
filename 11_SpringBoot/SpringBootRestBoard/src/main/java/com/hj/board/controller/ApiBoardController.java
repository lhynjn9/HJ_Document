package com.hj.board.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hj.board.model.dto.Board;
import com.hj.board.model.service.BoardService;

@RestController
@RequestMapping("/api")
public class ApiBoardController {
	
	@Autowired
	private BoardService boardService;

	// http://localhost:8080/api/board
	@GetMapping("/board")
	public ResponseEntity<List<Board>> list(
			@RequestParam(defaultValue="") String mode, 
			@RequestParam(defaultValue="") String keyword) {
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mode", mode);
		params.put("key", keyword); //boardMapper에서 key라고 명명
//		List<Board> list = boardService.getBoardList(params);

		return new ResponseEntity<List<Board>>(
				boardService.getBoardList(params), HttpStatus.OK
				);
	}
}
