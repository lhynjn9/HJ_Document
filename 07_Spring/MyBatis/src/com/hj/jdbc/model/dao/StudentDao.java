package com.hj.jdbc.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.hj.jdbc.model.dto.Student;

public interface StudentDao{
//	@Insert("INSERT INTO student VALUES (#{snum}, #{sname}, #{sgrage}")
	void insertStudent(Student student);
//	@Select("SELECT * FROM student WHERE snum=#{snum}")
	Student selectOne(int snum);
//	@Select("SELECT * FROM student")
	List<Student> selectAll();
	}