package com.hj.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hj.board.config.MyAppSqlConfig;
import com.hj.board.model.dao.BoardDao;
import com.hj.board.model.dto.Board;


@Service
public class BoardServiceImpl implements BoardService{
	
	//===============
	// 규격과 없이 만든 것, 서비스의 규격은 아님, 본 객체가 정상동작, 주입받기 위한 장치
	// 동작하기 위해 DAO가 필요함
	private BoardDao boardDao;
	public BoardServiceImpl() {
		boardDao = MyAppSqlConfig.getSession().getMapper(BoardDao.class);
	}
	
	// 주입받기 위해서 설정자가 필요
//	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		// 직접 boardDao를 getMapper해서 boardDao에 넣어주야아함
		this.boardDao = boardDao;
	}
	//==================
	
	@Override
	public List<Board> getBoardList() {
		// TODO Auto-generated method stub
		System.out.println("모든 게시물을 얻어옴");
		return boardDao.selectBoard();
	}

	// 게시글 읽기
	@Override
	public Board readBoard(int id) {
		// TODO Auto-generated method stub
		System.out.println("id에 해당하는 게시물을 읽습니다.");
		boardDao.updateViewCnt(id); //카운트를 고치고
		//고친 카운트로 dao를 통한 조회를 리턴함
		return boardDao.selectBoardById(id);
	}
	
	
	@Override
	public void writeBoard(Board board) {
		// TODO Auto-generated method stub
		System.out.println("게시물을 작성합니다.");
		boardDao.insertBoard(board);
	}

	@Override
	public void modityBoard(Board board) {
		// TODO Auto-generated method stub
		System.out.println("게시물을 수정합니다.");
		boardDao.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int id) {
		// TODO Auto-generated method stub
		System.out.println("게시물을 삭제합니다.");
		boardDao.deleteBoard(id);
	}

}
