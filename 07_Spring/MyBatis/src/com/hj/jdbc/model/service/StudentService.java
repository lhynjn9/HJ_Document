package com.hj.jdbc.model.service;

import java.util.List;

import com.hj.jdbc.model.dto.Student;

public interface StudentService{
	List<Student> getStudentList();
	
	// 한 학급의 학생들을 한번에 데이터베이스를 넣는 기능
	void registerClass(List<Student> list) throws Exception;
}