package com.hj.jdbc.model.dao;

import java.util.List;

import com.hj.jdbc.model.dto.Student;

public interface StudentDao{
	void insertStudent(Student student);
	Student selectOne(int snum);
	List<Student> selectAll();
	}