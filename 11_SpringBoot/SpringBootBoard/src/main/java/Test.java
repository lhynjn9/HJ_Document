import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hj.board.model.dto.User;
import com.hj.board.model.service.UserService;

public class Test {
	public static void main(String[] args) {
//		System.out.println("실행가능");
		ApplicationContext context 
		= new GenericXmlApplicationContext("root-context.xml");
		
//		BoardDao boardDao = context.getBean("boardDao", BoardDao.class);
//		
//		for(Board b : boardDao.selectList())
//			System.out.println(b);
		
		UserService userService = context.getBean("userService", UserService.class);
		User user = new User("iccak", "qwer1234", "Hong");
		try {
			userService.join(user, "안녕하세요");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
