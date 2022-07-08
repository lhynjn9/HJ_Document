// 11.

package com.hj.board.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hj.board.exception.BoardNotFoundException;
import com.hj.board.model.dto.Board;
import com.hj.board.model.service.BoardService;

@RestController 
@RequestMapping("/api") //기본경로 설정
public class BoardRestController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;
	
	// http://localhost:9999/api/board?mode=2&keyword=반
	// http://localhost:9999/swagger-ui/index.html
	@GetMapping("/board")
	public ResponseEntity<List<Board>> list(@RequestParam(defaultValue="")String mode, @RequestParam(defaultValue="")String keyword){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mode", mode);
		params.put("keyword", keyword);
		return new ResponseEntity<List<Board>>(
				boardService.getBoardList(params), HttpStatus.OK
				);
	}
	
	@PostMapping("/board")
	public ResponseEntity<String> write(Board board){
		boardService.writerBoard(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/board")
	public ResponseEntity<String> update(Board board){
		boardService.modifyBoard(board); // 결과 boolean
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	// 게시글 하나 가져오기
	@GetMapping("/board/{id}")
	public ResponseEntity<Board> detail(@PathVariable int id){
		try {
			return new ResponseEntity<Board>(boardService.getBoard(id), HttpStatus.OK);			
		} catch(Exception e) {
			throw new BoardNotFoundException(id + "게시글을 없습니다.");
		}
	}
	
	
	@DeleteMapping("/board/{id}")
	public ResponseEntity<String> delete(@PathVariable int id){
		// 결과가 boolean
		if(boardService.removeBoard(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
