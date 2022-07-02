package com.ss.hj.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.hj.dto.Book;
import com.ss.hj.model.repo.BookRepo;

/**
 * ������ ��ϵ� �� �ֵ��� @Service�� �����Ѵ�.
 */
@Service
public class BookServiceImpl implements BookService {
	/**
	 * has a ����� ����� BookRepo Ÿ���� repo�� �����Ѵ�.
	 */
	private BookRepo repo;
	
	/**
	 * setter�� ���� BookRepo�� ���Թ޴´�.
	 * @Autowired�� ���� BookRepo Ÿ���� ���� ���� �޴´�.
	 * @param repo
	 */
	@Autowired
	public void setBookRepo(BookRepo repo) {
		this.repo = repo;
	}
	public BookRepo getBookRepo() {
		return repo;
	}
	@Override
	public int insert(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(String isbn) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Book select(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Book> search() {
		// TODO Auto-generated method stub
		return null;
	}
}