package com.hj.board.model.service;

import com.hj.board.model.dto.User;

public interface UserService {
	// msg : 인삿말
	void join(User user, String msg) throws Exception;
	User login(String id, String pw) throws Exception;
}
