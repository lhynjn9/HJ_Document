package com.hj.board.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hj.board.model.dto.Board;

// BoardDao의 구현 클래스
// 데이터를 추가하고 삽입하고 삭제하는 등의 관점
@Repository
public class BoardDaoImpl implements BoardDao{

	// DAO : 데이터베이스와의 상호 작용
	// Service : 조회 : 게시글의 목록 조회
	// 데이터베이스에서 Board 테이블에 있는 데이터를 조회하는 관점
	@Override
	public List<Board> selectBoard() {
		// TODO Auto-generated method stub
		System.out.println("BoardDao selectBoard");
		return null;
	}

	// Service : 게시글 하나에 대한 정보를 Controller 에 가져다 주는 것이 목적 : 게시글을 읽는 접근
	// 해당 id에 맞는 레코드를 조회하는 것이 목적
	@Override
	public Board selectBoardById(int id) {
		// TODO Auto-generated method stub
		System.out.println("BoardDao selectBoardById");
		return null;
	}

	// Board 테이블에 하나의 레코드를 삽입하는 것이 목적
	@Override
	public void insertBoard(Board board) {
		// TODO Auto-generated method stub
		System.out.println("BoardDao insertBoard");
	}

	@Override
	public void updateViewCnt(int id) {
		// TODO Auto-generated method stub
		System.out.println("BoardDao updsateViewCnt");
		
	}

	@Override
	public void deleteBoard(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(Board board) {
		// TODO Auto-generated method stub
		
	}

}
