package com.hj.board.model.dao;

import java.util.List;

import com.hj.board.model.dto.Board;

// 데이터베이스와의 상호 작용
public interface BoardDao{
	
	// 전체 게시물 가져오기
	public List<Board> selectBoard();
	
	// 게시글 상세정보 가져오기
	public Board selectBoardById(int id);
	
	// 게시글 등록
	public void insertBoard(Board board);
	
	// 조회수 증가시키기
	public void updateViewCnt(int id);
	
	// 게시글 삭제
	public void deleteBoard(int id);
	
	// 게시글 수정
	public void updateBoard(Board board);
}