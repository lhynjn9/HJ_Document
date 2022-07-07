// 10.

package com.hj.board.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hj.board.model.dao.BoardDao;
import com.hj.board.model.dto.Board;

@Service // 서비스 등록
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	
	@Override
	public List<Board> getBoardList(HashMap<String, String> params) {
		return boardDao.selectList(params);
	}

	@Override
	public Board getBoard(int id) {
		this.updateCnt(id);
		return boardDao.selectOne(id);
	}

	@Override
	public void writerBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public boolean modifyBoard(Board board) {
		// 기존의 것을 가져옴
		Board originBoard = boardDao.selectOne(board.getId());
		// 조회한 것을 가져와서 입력받은 값을 넣어줌
		originBoard.setTitle(board.getTitle());
		originBoard.setContent(board.getContent());
		// 결과의 갯수..?
		return boardDao.updateBoard(originBoard) == 1;
	}

	@Override
	public boolean removeBoard(int id) {
		return boardDao.deleteBoard(id) == 1;
	}

	@Override
	public void updateCnt(int id) {
		Board board = boardDao.selectOne(id);
		board.setViewCnt(board.getViewCnt()+1);
		boardDao.updateBoard(board);
		
	}

}
