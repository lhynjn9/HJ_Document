package com.hj.jdbc.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.hj.jdbc.model.dto.Student;

// 드라이버 로딩 : spring
// 연결 생성 : spring
// DB연결 정보(url, username, password) : 개발자
// 구문객체생성 : spring
// 실행 sql: 개발자
// 결과 매핑 : spring


@Repository("studentDao")
public class StudentDaoImpl implements StudentDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertStudent(Student student) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO student VALUES(?,?,?)";
		jdbcTemplate.update(sql, student.getSnum(), student.getSname(), student.getSgrade());
	}

	@Override
	public Student selectOne(int snum) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM student WHERE snum = ?";
		return jdbcTemplate.queryForObject(sql, mapper, snum);
	}

	@Override
	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM student";
		return jdbcTemplate.query(sql, mapper);
	}
	
	// 한번만 만들면 되니까 굳이 implements안하고 익명 클래스
	RowMapper<Student> mapper = new RowMapper() {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Student student = new Student();
			student.setSnum(rs.getInt(1));
			student.setSname(rs.getString(2));
			student.setSgrade(rs.getInt(3));
			
			return student;
		}
		
	};
	
	
}