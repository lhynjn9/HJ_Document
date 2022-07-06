package com.hj.board.model.dao;

import com.hj.board.model.dto.User;

public interface UserDao {
	void insertUser(User user) throws Exception;
	User selectById(String id) throws Exception;
}
