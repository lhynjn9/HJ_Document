package com.hj.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hj.board.exception.PWIncorrectException;
import com.hj.board.exception.UserNotFoundException;
import com.hj.board.model.dao.BoardDao;
import com.hj.board.model.dao.UserDao;
import com.hj.board.model.dto.Board;
import com.hj.board.model.dto.User;
import com.hj.board.util.SHA256;

// 빈으로 등록
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private BoardDao boardDao;
	
	@Transactional // 가입인사를 했는데 id가 중복될 경우 작업이 모두 취소되어야 함
	@Override
	public void join(User user, String msg) throws Exception {
		// TODO Auto-generated method stub
		user.setPw(new SHA256().getHash(user.getPw()));
		Board b = new Board();
		b.setTitle(user.getUsername() + "가입인사입니다.");
		b.setContent(msg);
		b.setWriter(user.getUsername());
		boardDao.insertBoard(b);
		userDao.insertUser(user);
		
	}

	@Override
	public User login(String id, String pw) throws Exception {
		// TODO Auto-generated method stub
		User user = userDao.selectById(id);
		if(user == null)
			throw new UserNotFoundException();
		if(!user.getPw().equals(new SHA256().getHash(pw)))
			throw new PWIncorrectException();
		else
			return user;
	}

}
