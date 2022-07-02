package com.ss.hj.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.hj.dto.User;
import com.ss.hj.model.repo.UserRepo;

/**
 * ������ ��ϵ� �� �ֵ��� @Service�� �����Ѵ�.
 *
 */
@Service
public class UserServiceImpl implements UserService {
	/**
	 * has a ����� ����� UserRepo Ÿ���� repo�� �����Ѵ�.
	 */
	private UserRepo repo;
	
	/**
	 * �����ڸ� ���� UserRepo�� ���Թ޴´�.
	 * UserRepo Ÿ���� ���� ���Թޱ� ���� @Autowired�� ����Ѵ�.
	 * @param repo
	 */
	@Autowired
	public UserServiceImpl(UserRepo repo) {
		this.repo = repo;
	}
	@Override
	public User select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}