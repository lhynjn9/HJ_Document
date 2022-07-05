import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hj.jdbc.model.dao.StudentDao;
import com.hj.jdbc.model.dto.Student;
import com.hj.jdbc.model.service.StudentService;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		
//		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
//		
//		Student student = new Student(2, "KO", 10);
//		studentDao.insertStudent(student);
//		
//		for(Student s : studentDao.selectAll())
//			System.out.println(s);
//		System.out.println(studentDao.selectOne(2));
		
		StudentService studentService = context.getBean("studentService", StudentService.class);
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(8, "Kim", 5));
		list.add(new Student(9, "lim", 5));
		list.add(new Student(10, "jim", 5));
		list.add(new Student(11, "yim", 5));
		list.add(new Student(12, "pim", 5));
		try {
			studentService.registerClass(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		for(Student s : studentService.getStudentList())
			System.out.println(s);
		
		
	}
}
