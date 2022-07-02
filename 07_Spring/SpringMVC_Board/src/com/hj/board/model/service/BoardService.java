package com.hj.board.model.service;

import java.util.List;
import com.hj.board.model.dto.Board;

// 필요한 기능의 명시
// 기능에 대한 영역(읽고 쓰고 조회 등의 관점)
// Board 서비스의 규격
public interface BoardService {
	// 모든 게시물 조회
	// 게시물의 리스트를 얻어오는 관점
	List<Board> getBoardList();
	
	// 글번호에 해당하는 게시물을 읽는 기능
	Board readBoard(int id);
	
	// 글하나를 작성하는 기능
	void writeBoard(Board board);
	
	// 글내용을 수정하는 기능
	void modityBoard(Board board);
	
	// 글번호에 해당하는 게시물을 삭제하는 기능
	void deleteBoard(int id);
}
