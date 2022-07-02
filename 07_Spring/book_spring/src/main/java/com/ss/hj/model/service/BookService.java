package com.ss.hj.model.service;

import java.util.List;

import com.ss.hj.dto.Book;

public interface BookService {
	
	int insert(Book book);
	int update(Book book);
	int delete(String isbn);
	Book select(String isbn);
	List<Book> search();
}