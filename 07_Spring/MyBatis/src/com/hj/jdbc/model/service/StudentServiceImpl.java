package com.hj.jdbc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hj.jdbc.model.dao.StudentDao;
import com.hj.jdbc.model.dto.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Student> getStudentList() {
		// TODO Auto-generated method stub
		return studentDao.selectAll();
	}
	
	@Transactional
	@Override
	public void registerClass(List<Student> list) throws Exception {
		// TODO Auto-generated method stub
		for(Student s : list)
			studentDao.insertStudent(s);
		
	}

} 
