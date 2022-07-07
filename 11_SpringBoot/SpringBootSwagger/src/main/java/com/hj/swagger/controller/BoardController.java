package com.hj.swagger.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hj.swagger.model.dto.Board;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@ApiIgnore // api 목록에서 제외
@Api(tags="게시글") // 이름 명명
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@ApiOperation(
			value = "게시글 조회",
			notes = "페이지에 해당하는 게시글 목록을 반환"
			) // value = url의 설명, notes = 부가적인 설명 작성 가능
	// 매개변수에 대한 설명
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageNo", value="페이지 번호", required=true ),
		@ApiImplicitParam(name="key", value="제목|내용|제목+내용"),
		@ApiImplicitParam(name="word", value="검색어")
	})
	@GetMapping("/board")
	public ResponseEntity<List<Board>> retrieveBoard(
			int pageNo, 
			String key, 
			String word){
		return new ResponseEntity<List<Board>>(
				Arrays.asList(
						new Board(1, "ssafy", "swagger", "swagger 연숩"),
						new Board(2, "ssafy2", "swagger2", "swagger 연숩2")
				),
				HttpStatus.OK
			);
	}
	
}
