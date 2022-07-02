package com.ss.hj.model.repo;

import com.ss.hj.dto.User;

public interface UserRepo {
	User select(String id);

}