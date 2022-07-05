package com.hj.jdbc.model.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hj.jdbc.model.dto.Student;

//mybatis1
//mapper.xml에 sql 구문정보를 작성하고
//Dao에서 SqlSessionFactory를 소유하면서
//각각의 필요한 기능에서 자신에 맞는 mapper 구문을 호출하도록 구현

//mybatis2
//sql구문을 자신이 호출되고자하는 인터페이스 함수 위에 에노테이션으로 작성
//Dao에서 SqlSessionFactory를 소유하면서
//Mapper객체를 (마이바티스가 만들어주는 Dao구현체)를 얻어와 호출

//mybatis3
//xml에 sql구문 정보를 작성하되
//짝궁될 인터페이서의 풀패키지명을 namespace로
//각각의 구문의 아이디를  짝궁의 함수명으로 매칭
//Dao에서 SqlSessionFactory를 소유하면서
//Mapper객체(마이바티스가 만들어주는 Dao 구현체)를 얻어와 호출

@Repository
public class StudentDaoImpl implements StudentDao{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	public StudentDaoImpl() {
		InputStream inputStream;
//		try {
//			String resource = "configuartion.xml";
//			inputStream = Resources.getResourceAsStream(resource);
//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	@Override
	public void insertStudent(Student student) {
		// TODO Auto-generated method stub
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// namespaece.id
//			session.insert("student.insert", student);
			session.getMapper(StudentDao.class).insertStudent(student);
		}
	}
	@Override
	public Student selectOne(int snum) {
		// TODO Auto-generated method stub
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// namespaece.id
//			return session.selectOne("student.selectOne", snum);
			return session.getMapper(StudentDao.class).selectOne(snum);
		}
	}
	@Override
	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// namespaece.id
			return session.getMapper(StudentDao.class).selectAll();
		}
	}
}
