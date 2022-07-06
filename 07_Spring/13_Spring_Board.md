# Spring Board

> #### MySQL

1. 테이블 생성

   ```sql
   drop database if exists hj_board;
   create database hj_board default CHARACTER set utf8mb4;
   
   use hj_board;
   
   create table board(
   	id int AUTO_INCREMENT,
   	writer VARCHAR(30) NOT NULL,
       title VARCHAR(200) NOT NULL,
       content TEXT,
       view_cnt INT DEFAULT 0,
       reg_date TIMESTAMP DEFAULT now(),
       file_name varchar(50), 
       file_uri varchar(500), -- 특정 경로 + uuid 
       PRIMARY KEY (id)
   );
   
   INSERT INTO board (title, writer, content)
   VALUES ('안녕하세요', '이현정', '반갑습니다'),
   ('DB', '현정이', 'ez..?'),
   ('벌써금용일?', '김철수', '월화수목금토일');
   
   
   CREATE TABLE b_user(
       seq int AUTO_INCREMENT,
       id VARCHAR(30) not null UNIQUE,
       pw VARCHAR(64) not null,
       username varchar(30) not null,
       PRIMARY KEY(seq)
   );
   
   select * from board;
   
   
   ```

   

2. Database - Connect to Database

3. Query - Execute(All or Selection) : 테이블 생성



> #### Spring

1. Spring legacy project : SpringBoard 생성

2. 우클릭 - Properties - Web Project Settings - board로 변경

3. pom.xml 변경

   ```xml
   	<artifactId>board</artifactId>
   	<name>SpringBoard</name>
   ```

4. pom.xml 버전 확인

   ```xml
   <properties>
   		<java-version>1.8</java-version>
   		<org.springframework-version>5.3.17</org.springframework-version>
   		<org.aspectj-version>1.9.8</org.aspectj-version>
   		<org.slf4j-version>1.7.36</org.slf4j-version>
   	</properties>
   ```

5. pom.xml : mysql, mybatis, mybatis-spring, fileupload, springjdbc 추가

   ```xml
   <dependency>
   			<groupId>org.springframework</groupId>
   			<artifactId>spring-jdbc</artifactId>
   			<version>${org.springframework-version}</version>
   		</dependency>
   <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
   		<dependency>
   		    <groupId>mysql</groupId>
   		    <artifactId>mysql-connector-java</artifactId>
   		    <version>8.0.28</version>
   		</dependency>
   		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
   		<dependency>
   		    <groupId>org.mybatis</groupId>
   		    <artifactId>mybatis</artifactId>
   		    <version>3.5.9</version>
   		</dependency>
   		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
   		<dependency>
   		    <groupId>org.mybatis</groupId>
   		    <artifactId>mybatis-spring</artifactId>
   		    <version>2.0.7</version>
   		</dependency>
   
   <dependency>
   		    <groupId>commons-fileupload</groupId>
   		    <artifactId>commons-fileupload</artifactId>
   		    <version>1.4</version>
   		</dependency>
   
   ```

6. src/main/resources/log4j.xml : 아래와 같이 변경

   ```xml
   <!-- Application Loggers -->
   	<logger name="com.hj.board">
   		<level value="info" />
   	</logger>
   ```

   

7. 기존 HomeController.java와 그 패키지 삭제

8. 아래의 것들을 패키지 안에 생성할 예정

   1. Controller
   2. model : dto, dao, service

9. src/main/java/com.hj.board.model.dto/User.java 생성

   ```java
   package com.hj.board.model.dto;
   
   public class User {
   	// Database와 이름을 맞춰주는 것이 편함
   	private int seq;
   	private String id;
   	private String pw;
   	private String username;
   	
   	public User(String id, String pw, String username) {
   		this.id = id;
   		this.pw = pw;
   		this.username = username;
   	}
   	
   	
   	public User() {
   		
   	}
   
   	public int getSeq() {
   		return seq;
   	}
   	public void setSeq(int seq) {
   		this.seq = seq;
   	}
   	public String getId() {
   		return id;
   	}
   	public void setId(String id) {
   		this.id = id;
   	}
   	public String getPw() {
   		return pw;
   	}
   	public void setPw(String pw) {
   		this.pw = pw;
   	}
   	public String getUsername() {
   		return username;
   	}
   	public void setUsername(String username) {
   		this.username = username;
   	}
   	@Override
   	public String toString() {
   		return "User [seq=" + seq + ", id=" + id + ", pw=" + pw + ", username=" + username + "]";
   	}
   }
   ```

10. src/main/java/com.hj.board.model.dao/UserDao.java(interface) 생성

    - Mapper Injection 할거라 interface만 필요

    - 필요한 기능 구현 : 회원가입, 로그인

      ```java
      package com.hj.board.model.dao;
      
      import com.hj.board.model.dto.User;
      
      public interface UserDao {
      	void insertUser(User user) throws Exception;
      	User selectById(String id) throws Exception;
      }
      
      ```

11. src/main/resources/mapper/userMapper.xml 생성

    - namespace는 인터페이스의 풀패키지 명으로, 이름이 같아야 mapper injection 가능

      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        
        
        <mapper namespace="com.hj.board.model.dao.UserDao">
        	<insert id="insertUser" parameterType="user">
        		<!-- 0을 주면 auto increment -->
        		INSERT INTO b_user VALUES(0, #{id}, #{pw}, #{username})
        	</insert>
        	<select id="selectById" resultType="user" parameterType="String">
        		SELECT * FROM b_user WHERE id = LIKE #{id}
        	</select>
        </mapper>
      ```

      

12. src/main/java/com.hj.board.model.dto/Board.java 생성

    ```java
    package com.hj.board.model.dto;
    
    public class Board {
    	private int id;
    	private String writer;
    	private String title;
    	private String content;
    	// resultmap에서 맞춰줘야 함
    	private int viewCnt;
    	private String regDate;
    	private String filename;
    	private String fileUri;
    	public int getId() {
    		return id;
    	}
    	public void setId(int id) {
    		this.id = id;
    	}
    	public String getWriter() {
    		return writer;
    	}
    	public void setWriter(String writer) {
    		this.writer = writer;
    	}
    	public String getTitle() {
    		return title;
    	}
    	public void setTitle(String title) {
    		this.title = title;
    	}
    	public String getContent() {
    		return content;
    	}
    	public void setContent(String content) {
    		this.content = content;
    	}
    	public int getViewCnt() {
    		return viewCnt;
    	}
    	public void setViewCnt(int viewCnt) {
    		this.viewCnt = viewCnt;
    	}
    	public String getRegDate() {
    		return regDate;
    	}
    	public void setRegDate(String regDate) {
    		this.regDate = regDate;
    	}
    	public String getFilename() {
    		return filename;
    	}
    	public void setFilename(String filename) {
    		this.filename = filename;
    	}
    	public String getFileUri() {
    		return fileUri;
    	}
    	public void setFileUri(String fileUri) {
    		this.fileUri = fileUri;
    	}
    	
    	@Override
    	public String toString() {
    		return "Board [id=" + id + ", writer=" + writer + ", title=" + title + ", content=" + content + ", viewCnt="
    				+ viewCnt + ", regDate=" + regDate + ", filename=" + filename + ", fileUri=" + fileUri + "]";
    	}
    	
    }
    
    ```



13. src/main/java/com.hj.board.model.dao/BoradDao.java(interface) 생성

    ```java
    package com.hj.board.model.dao;
    
    import java.util.List;
    
    import com.hj.board.model.dto.Board;
    
    public interface BoardDao {
    	// 게시글 수정 update
    	void updateBoard(Board board);
    	// 게시글 삭제 delete
    	void deleteBoard(int id);
    	// 게시글 추가 insert
    	void insertBoard(Board board);
    	// 게시글 조회 selectOne
    	Board selectOne(int id);
    	// 게시글 목록 selectList
    	List<Board> selectList();
    	
    }
    ```



14. src/main/resources/mapper/boardMapper.xml 생성

    - namespace는 인터페이스의 풀패키지 명으로, 이름이 같아야 mapper injection 가능

      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        
        <mapper namespace="com.hj.board.model.dao.BoardDao">
        	<resultMap type="board" id="boardMap">
        		<!-- 테이블, dto -->
        		<id column="id" property="id"/>
        		<result column="writer" property="writer"/>
        		<result column="title" property="title"/>
        		<result column="content" property="content"/>
        		<result column="view_cnt" property="viewCnt"/>
        		<result column="regDate" property="regDate"/>
        		<result column="file_name" property="fileName"/>
      	  	<result column="file_uri" property="fileUri"/>
        	</resultMap>
        	
        	<insert id="insertBoard" parameterType="board">
        		INSERT INTO board VALUES
        		(0, #{writer}, #{title}, #{content}, #{viewCnt}, now(), #{fileName}, #{fileUri})
        	</insert>
        	
        	<update id="updateBoard" parameterType="board">
        		UPDATE board
        		SET
        		title = #{title},
        		writer = #{writer},
        		content = #{content},
        		reg_date = #{regDate},
        		view_cnt = #{viewCnt},
        		file_name = #{fileName},
        		file_uri = #{fileUri},
        		WHERE id = #{id}
        	</update>
        	
        	<delete id="deleteBoard" parameterType="int">
        		DELETE FROM board WHERE id= #{id}
        	</delete>
        	
        	<select id="selectOne" resultMap="boardMap" parameterType="int">
        		SELECT id, title, writer, content, view_cnt, date_format(reg_date, '%Y-%m-%d %H%i%s'), file_name, file_uri,
        		FROM board
        		WHERE id = #{id}
        	</select>
        	
        	<select id="selectList" resultMap="boardMap">
        		SELECT id, title, writer, content, view_cnt, date_format(reg_date, '%Y-%m-%d %H%i%s'), file_name, file_uri
              FROM board
        	</select>
        </mapper>
      ```

15. src/main/java/(default package)/Test.java 생성

    ```java
    
    public class Test {
    	public static void main(String[] args) {
    		System.out.println("실행가능");
    	}
    }
    
    ```

16. src/main/webapp/WEB-INF/spring/root-context.xml

    - Namespace에서 tx추가

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xmlns:tx="http://www.springframework.org/schema/tx"
    	xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
    		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
    	
    	<!-- Root Context: defines shared resources visible to all other web components -->
    	<!-- - DataSource 빈 등록 -->
    	<bean class="org.springframework.jdbc.datasource.DriverManagerDataSource"
    	id = "dataSource">
    		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
    		<property name="url" value="jdbc:mysql://localhost:3306/hj_board?serverTimezone=UTC"></property>
    		<property name="username" value="root"></property>
    		<property name="password" value="0000"></property>
    	</bean>
    	<!-- - SqlSessionFactory 빈 등록 -->
    	<bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactory">
    		<property name="dataSource" ref="dataSource"></property>
    		<property name="typeAliasesPackage" value="com.hj.board.model.dto"></property>
    		<property name="mapperLocations" value="classpath:/mapper/*Mapper.xml"></property>
    	</bean>
    	<!-- - Mapper 빈 등록 -->
    	<bean class="org.mybatis.spring.mapper.MapperFactoryBean">
    		<property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
    		<property name="mapperInterface" value="com.hj.board.model.dao.UserDao"></property>
    	</bean>
    	<bean class="org.mybatis.spring.mapper.MapperFactoryBean">
    		<property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
    		<property name="mapperInterface" value="com.hj.board.model.dao.BoardDao"></property>
    	</bean>
    	<!-- - TransactionManager 빈 등록 -->
    	<bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
    	id="transactionManager">
    		<property name="dataSource" ref="dataSource"></property>
    	</bean>
    	<!-- - 트랜잭션 annotation 설정 -->
    	<tx:annotation-driven transaction-manager="transactionManager"/>
    		
    </beans>
    
    ```

17. Test.java 에서 root-context.xml에 접근가능하게 만들기 위해 root-context.xml을 src/main/resources/root-context.xml에 복사본 생성 : 테스트용 임시

    - 복사본은 JAVA에서 접근할 것
    - 원본은 WEB에서 접근할 것

18. Test.java

    ```java
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    import com.hj.board.model.dao.BoardDao;
    import com.hj.board.model.dto.Board;
    
    public class Test {
    	public static void main(String[] args) {
    //		System.out.println("실행가능");
    		ApplicationContext context 
    		= new GenericXmlApplicationContext("root-context.xml");
    		
    		BoardDao boardDao = context.getBean("boardDao", BoardDao.class);
    		
    		for(Board b : boardDao.selectList())
    			System.out.println(b);
    	}
    }
    
    ```

    

19. src/main/java/com/hj/board/model/service/UserService.java(인터페이스) 생성

    ```java
    package com.hj.board.model.service;
    
    import com.hj.board.model.dto.User;
    
    public interface UserService {
    	// msg : 인삿말
    	void join(User user, String msg) throws Exception;
    	void login(String id, String pw) throws Exception;
    }
    
    ```

20. src/main/java/com/hj/board/model/service/UserServiceImpl.java 생성

    ```java
    package com.hj.board.model.service;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    
    import com.hj.board.exception.PWIncorrectException;
    import com.hj.board.exception.UserNotFoundException;
    import com.hj.board.model.dao.BoardDao;
    import com.hj.board.model.dao.UserDao;
    import com.hj.board.model.dto.Board;
    import com.hj.board.model.dto.User;
    
    // 빈으로 등록
    @Service("userService")
    public class UserServiceImpl implements UserService{
    
    	@Autowired
    	private UserDao userDao;
    	@Autowired
    	private BoardDao boardDao;
    	
    	@Transactional // 가입인사를 했는데 id가 중복될 경우 작업이 모두 취소되어야 함
    	@Override
    	public void join(User user, String msg) throws Exception {
    		// TODO Auto-generated method stub
    		
    		Board b = new Board();
    		b.setTitle(user.getUsername() + "가입인사입니다.");
    		b.setContent(msg);
    		b.setWriter(user.getUsername());
    		boardDao.insertBoard(b);
    		userDao.insertUser(user);
    		
    	}
    
    	@Override
    	public User login(String id, String pw) throws Exception {
    		// TODO Auto-generated method stub
    		User user = userDao.selectById(id);
    		if(user == null)
    			throw new UserNotFoundException();
    		if(!user.getPw().equals(pw))
    			throw new PWIncorrectException();
    		else
    			return user;
    	}
    
    }
    
    ```

21. src/main/java/com/hj/board/exception/UserNotFoundException.java 생성

    ```java
    package com.hj.board.exception;
    
    public class UserNotFoundException extends Exception{
    	public UserNotFoundException() {
    		super("사용자를 찾을 수 없습니다.");
    	}
    }
    
    ```

22. src/main/java/com/hj/board/exception/PWIncorrectException.java 생성

    ```java
    package com.hj.board.exception;
    
    public class PWIncorrectException extends Exception{
    	public PWIncorrectException() {
    		super("비밀번호가 틀립니다.");
    	}
    }
    
    ```

23. src/main/resources/root-context.xml

    - Namespace에 context 추가

      ```java
      <context:component-scan base-package="com.hj.board.model.service"></context:component-scan>
      ```

24. Test.java

    ```java
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
    
    ```



25. src/main/java/com/hj/board/util/SHA256.java 생성

    ```java
    package com.hj.board.util;
    
    import java.security.MessageDigest;
    import java.security.NoSuchAlgorithmException;
    
    public class SHA256{
    	public String getHash(String msg) throws NoSuchAlgorithmException{
    		MessageDigest md = MessageDigest.getInstance("SHA-256");
    		md.update(msg.getBytes());
    		md.digest();
    		return byteToHex(md.digest());
    	}
    	
    	private String byteToHex(byte[] bytes) {
    		StringBuilder builder = new StringBuilder();
    		for(byte b: bytes)
    			builder.append(String.format("%02x", b));
    		return builder.toString();
    	}
    }
    ```



26. src/main/java/com/hj/board/model/service/UserServiceImpl.java에 sha256 추가

    ```java
    package com.hj.board.model.service;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    
    import com.hj.board.exception.PWIncorrectException;
    import com.hj.board.exception.UserNotFoundException;
    import com.hj.board.model.dao.BoardDao;
    import com.hj.board.model.dao.UserDao;
    import com.hj.board.model.dto.Board;
    import com.hj.board.model.dto.User;
    import com.hj.board.util.SHA256;
    
    // 빈으로 등록
    @Service("userService")
    public class UserServiceImpl implements UserService{
    
    	@Autowired
    	private UserDao userDao;
    	@Autowired
    	private BoardDao boardDao;
    	
    	@Transactional // 가입인사를 했는데 id가 중복될 경우 작업이 모두 취소되어야 함
    	@Override
    	public void join(User user, String msg) throws Exception {
    		// TODO Auto-generated method stub
    		user.setPw(new SHA256().getHash(user.getPw()));
    		Board b = new Board();
    		b.setTitle(user.getUsername() + "가입인사입니다.");
    		b.setContent(msg);
    		b.setWriter(user.getUsername());
    		boardDao.insertBoard(b);
    		userDao.insertUser(user);
    		
    	}
    
    	@Override
    	public User login(String id, String pw) throws Exception {
    		// TODO Auto-generated method stub
    		User user = userDao.selectById(id);
    		if(user == null)
    			throw new UserNotFoundException();
    		if(!user.getPw().equals(new SHA256().getHash(pw)))
    			throw new PWIncorrectException();
    		else
    			return user;
    	}
    
    }
    
    ```



27.    src/main/webapp/WEB-INF/spring/root-context.xml 삭제 후 src/main/resources/root-context.xml를 이용해서 src/main/webapp/WEB-INF/spring/root-context.xml 재 생성

28. src/main/webapp/WEB-INF/views/home.jsp 삭제

29. src/main/java/com/hj/board/controller/HomeController.java 생성

    ```java
    package com.hj.board.controller;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    @Controller
    public class HomeController{
    	
    	// localhost:8080/board
    	@RequestMapping("/")
    	public String home() {
    		// /WEB-INF/views/home.jsp로 forward
    		return "home";
    	}
    	
    }
    ```

30. src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 수정

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans:beans xmlns="http://www.springframework.org/schema/mvc"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xmlns:beans="http://www.springframework.org/schema/beans"
    	xmlns:context="http://www.springframework.org/schema/context"
    	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
    		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
    		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    
    	<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
    	
    	<!-- Enables the Spring MVC @Controller programming model -->
    	<annotation-driven />
    
    	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
    	<resources mapping="/resources/**" location="/resources/" />
    
    	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
    	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    		<beans:property name="prefix" value="/WEB-INF/views/" />
    		<beans:property name="suffix" value=".jsp" />
    	</beans:bean>
    	
    	<context:component-scan base-package="com.hj.board.controller" />
    	
    	
    	
    </beans:beans>
    
    ```

31. src/main/webapp/WEB-INF/views/home.jsp 생성

    ```jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
     	<%@include file="/WEB-INF/views/common/header.jsp" %>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    </head>
    <body>
    <!-- context 경로/BoardController의 RequestMapping/BoardController의 GetMapping -->
    <!-- {pageContext.request.contextPath } = context 경로 : 너무 김, 저장해서 사용하는게 좋을 듯 -->
    <!-- 33에서 해결 -->
    	<!-- <a href="${pageContext.request.contextPath }/board/list">게시글 보기</a> -->
    	<a href="${root }/board/list">게시글 보기</a>
    </body>
    </html>
    ```

32. src/main/java/com/hj/board/controller/BoardController.java 생성

    ```java
    package com.hj.board.controller;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    @Controller
    @RequestMapping("/board")
    public class BoardController {
    	//localhost:8080/board/board/list
    	@GetMapping("list")
    	public String list() {
    		
    	}
    }
    
    ```

33. src/main/webapp/WEB-INF/views/common/header.jsp 생성

    ```jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!-- 31의 {pageContext.request.contextPath } = context 경로 : 너무 김, 저장해서 사용하는게 좋을 듯 => 해결-->
    <c:set var = "root" value="${pageContext.request.contextPath}"></c:set>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
    ```

    

34. src/main/java/com/hj/board/model/service/BoardService.java 사용

    ```java
    package com.hj.board.model.service;
    
    import java.util.List;
    
    import com.hj.board.model.dto.Board;
    
    public interface BoardService {
    	// 글쓰기
    	void writeBoard(Board board);
    	// 글수정
    	void modifyBoard(Board board);
    	// 글삭제
    	void deleteBoard(int id);
    	// 조회수 증가
    	void updateCnt(int id);
    	// 글 얻어오기
    	Board getBoardById(int id);
    	// 글 읽기 : 글 얻어오기, 조회수 증가 사용
    	Board readBoard(int id);
    	// 모든 게시물 조회
    	List<Board> getBoardList();
    }
    
    ```

35. src/main/resources/mapper/boardMapper.xml  수정

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper
      PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
      
      <mapper namespace="com.hj.board.model.dao.BoardDao">
      	<resultMap type="board" id="boardMap">
      		<!-- 테이블, dto -->
      		<id column="id" property="id"/>
      		<result column="writer" property="writer"/>
      		<result column="title" property="title"/>
      		<result column="content" property="content"/>
      		<result column="view_cnt" property="viewCnt"/>
      		<result column="regDate" property="regDate"/>
      		<result column="file_name" property="fileName"/>
    	  	<result column="file_uri" property="fileUri"/>
      	</resultMap>
      	
      	<insert id="insertBoard" parameterType="board">
      		INSERT INTO board VALUES
      		(0, #{writer}, #{title}, #{content}, #{viewCnt}, now(), #{fileName}, #{fileUri})
      	</insert>
      	
      	<update id="updateBoard" parameterType="board">
      		UPDATE board
      		SET
      		title = #{title},
      		writer = #{writer},
      		content = #{content},
      		view_cnt = #{viewCnt},
      		file_name = #{fileName},
      		file_uri = #{fileUri},
      		WHERE id = #{id}
      	</update>
      	
      	<delete id="deleteBoard" parameterType="int">
      		DELETE FROM board WHERE id= #{id}
      	</delete>
      	
      	<select id="selectOne" resultMap="boardMap" parameterType="int">
      		SELECT id, title, writer, content, view_cnt, date_format(reg_date, '%Y-%m-%d %H%i%s'), file_name, file_uri,
      		FROM board
      		WHERE id = #{id}
      	</select>
      	
      	<select id="selectList" resultMap="boardMap" parameterType="java.util.HashMap">
      	<!-- HashMap : 키와 Value 값을 사용 가능 -->
      		SELECT id, title, writer, content, view_cnt, date_format(reg_date, '%Y-%m-%d %H%i%s'), file_name, file_uri
      		FROM board
      		<if test="mode == 1">
      			WHERE title LIKE CONCAT('%', #{key}, '%')
      		</if>
      		<if test="mode == 2">
      		<!-- $ : 타입에 상관없이 받음 -->
      			WHERE content LIKE '%${key}%'
      		</if>
      		<if test="mode==3">
      			WHERE title LIKE CONCAT('%', #{key}, '%')
      			OR content LIKE '%${key}%'
      		</if>
      	</select>
      </mapper>
    ```
    
36. src/main/java/com/hj/board/model/dao/BoardDao.java 수정

    ```java
    package com.hj.board.model.dao;
    
    import java.util.HashMap;
    import java.util.List;
    
    import com.hj.board.model.dto.Board;
    
    public interface BoardDao {
    	// 게시글 수정 update
    	void updateBoard(Board board);
    	// 게시글 삭제 delete
    	void deleteBoard(int id);
    	// 게시글 추가 insert
    	void insertBoard(Board board);
    	// 게시글 조회 selectOne
    	Board selectOne(int id);
    	// 게시글 목록 selectList
    	List<Board> selectList(HashMap<String, String> params);
    	
    }
    ```

37. src/main/java/com/hj/board/model/service/BoardService.java 수정

    ```java
    package com.hj.board.model.service;
    
    import java.util.HashMap;
    import java.util.List;
    
    import com.hj.board.model.dto.Board;
    
    public interface BoardService {
    	// 글쓰기
    	void writeBoard(Board board);
    	// 글수정
    	void modifyBoard(Board board);
    	// 글삭제
    	void deleteBoard(int id);
    	// 조회수 증가
    	void updateCnt(int id);
    	// 글 얻어오기
    	Board getBoardById(int id);
    	// 글 읽기 : 글 얻어오기, 조회수 증가 사용
    	Board readBoard(int id);
    	// 모든 게시물 조회
    	List<Board> getBoardList(HashMap<String, String> params);
    }
    ```

38. src/main/java/com/hj/board/model/service/BoardServiceImpl.java 생성

    ```java
    package com.hj.board.model.service;
    
    import java.io.File;
    import java.util.HashMap;
    import java.util.List;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    
    import com.hj.board.model.dao.BoardDao;
    import com.hj.board.model.dto.Board;
    
    @Service
    public class BoardServiceImpl implements BoardService{
    	
    	@Autowired
    	private BoardDao boardDao;
    	
    	@Override
    	public void writeBoard(Board board) {
    		// TODO Auto-generated method stub
    		boardDao.insertBoard(board);
    		
    	}
    
    	@Override
    	public void modifyBoard(Board board) {
    		// TODO Auto-generated method stub
    		// 원래 게시글을 가져와 제목과 내용만 수정
    		Board originalBoard = boardDao.selectOne(board.getId());
    		originalBoard.setTitle(board.getTitle());
    		originalBoard.setContent(board.getContent());
    		boardDao.updateBoard(originalBoard);
    	}
    
    	@Override
    	public void deleteBoard(int id) {
    		// TODO Auto-generated method stub
    		Board board = boardDao.selectOne(id);
    		if(board.getFileUri() != null) {
    			new File(board.getFileUri()).delete();
    		}
    		boardDao.deleteBoard(id);
    	}
    
    	@Override
    	public void updateCnt(int id) {
    		// TODO Auto-generated method stub
    		Board board = boardDao.selectOne(id);
    		board.setViewCnt(board.getViewCnt()+1);
    		boardDao.updateBoard(board);
    	}
    
    	@Override
    	public Board getBoardById(int id) {
    		// TODO Auto-generated method stub
    		return boardDao.selectOne(id);
    	}
    
    	@Override
    	public Board readBoard(int id) {
    		// TODO Auto-generated method stub
    		this.updateCnt(id);
    		return boardDao.selectOne(id);
    	}
    
    	@Override
    	public List<Board> getBoardList(HashMap<String, String> params) {
    		// TODO Auto-generated method stub
    		return boardDao.selectList(params);
    	}
    
    }
    
    ```
    
39. src/main/webapp/WEB-INF/views/board/list.jsp 생성

    ```jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <%@include file="../common/header.jsp" %>
    </head>
    <body>
    	<div class="containter">
    		<h2>자유게시판</h2>
    		<hr>
    		<div>
    			<table class="table text-center">
    				<tr>
    					<th>번호</th>
    					<th>제목</th>
    					<th>글쓴이</th>
    					<th>조회수</th>
    					<th>등록일</th>
    				</tr>
    				<!-- 게시물 조회 시, list라는 키 값으로 넣어주어야 함 -->
    				<c:forEach items="${list }" var="board">
    					<tr>
    						<td>${board.id }</td>
    						<td>
    							<!-- 제목이 눌리때 detail로 가면 id로 구별 -->
    							<a href="${root }/board/detail&id=${board.id }"> ${board.title }
    						</a></td>
    						<td>${board.writer }</td>
    						<td>${board.viewCnt }</td>
    						<td>${board.regDate }</td>
    					</tr>
    				</c:forEach>
    			</table>
    			<div>
    				<form action="${root }/board/list">
    					<select name="mode">
    						<option value="1">제목
    						<option value="2">내용
    						<option value="3">제목+내용
    					</select>
    					<input type="text" name="keyword">
    					<input type="submit" name="검색">
    				</form>
    			</div>
    			<div class="d-flex justify-content-end">
    				<a class="btn btn-outline-danger" href="writeform">등록</a>
    			</div>
    		</div>
    	</div>
    </body>
    </html>
    ```

40. src/main/webapp/WEB-INF/views/board/writeform.jsp 생성

    ```jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>글 작성</title>
    <%@incldue file = '/WEB-INF/views/common/header.jsp' %>
    </head>
    <body>
    	<div class="container">
    		<h2>자유게시판</h2>
    		<!-- 버튼이 눌리면 write로 모든 정보를 가지고 가게 됨 -->
    		<!-- mutipart : 파일 업로드 가능 -->
    		<form action="${root }/board/write" method="POST" enctype="multipart/form-data">
    			<input type="hidden" name="act" value="write" />
    			<div class="mb-3">
    				<label for="title" class="form-label">글제목</label>
    				<input type="text" class="form-control" id="title" name="title">
    			</div>
    			<div class="mb-3">
    				<label for="writer" class="form-label">글쓴이</label>
    				<input type="text" clas="form-control" id="writer" name="writer">
    			</div>
    			<div class="mb-3">
    				<label for="content" class="form-label">글내용</label>
    				<textarea class="form-control" id="content" name="content">
    			</div>
    			<div class="mb-3">
    				<input class="form-control" type="file" name="upload_file">
    			</div>
    			<button class="btn btn-primary">등록</button>
    		</form>
    	</div>
    </body>
    </html>
    ```

    

41. src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 수정

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans:beans xmlns="http://www.springframework.org/schema/mvc"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xmlns:beans="http://www.springframework.org/schema/beans"
    	xmlns:context="http://www.springframework.org/schema/context"
    	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
    		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
    		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    
    	<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
    	
    	<!-- Enables the Spring MVC @Controller programming model -->
    	<annotation-driven />
    
    	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
    	<resources mapping="/resources/**" location="/resources/" />
    
    	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
    	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    		<beans:property name="prefix" value="/WEB-INF/views/" />
    		<beans:property name="suffix" value=".jsp" />
    	</beans:bean>
    	
    	<context:component-scan base-package="com.hj.board.controller" />
    	
    	<beans:bean 
    	id="multipartResolver"
    	class="org.springframework.web.multipart.commons.CommonsMultipartResolver">	
    		<beans:property name="maxUploadSize" value="10485760"></beans:property>
    		<beans:property name="defaultEncoding" value="UTF-8"></beans:property>
    	</beans:bean>
    	
    	
    	
    </beans:beans>
    
    ```

    

42. src/main/java/com/hj/board/controller/HomeController.java 수정

    ```java
    package com.hj.board.controller;
    
    import java.io.File;
    import java.io.IOException;
    import java.util.HashMap;
    import java.util.List;
    import java.util.UUID;
    
    import javax.servlet.ServletContext;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.util.FileCopyUtils;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.multipart.MultipartFile;
    
    import com.hj.board.model.dto.Board;
    import com.hj.board.model.service.BoardService;
    
    @Controller
    @RequestMapping("/board")
    public class BoardController {
    	//localhost:8080/board/board/list
    	
    	// 파일 업로드
    	@Autowired
    	private ServletContext servletContext;
    	
    	@Autowired
    	private BoardService boardService;
    
    	@GetMapping("list")
    	// 기본 list 조회 또는 제목과 내용에 해당하는 파라미터가 있는 조회로 나뉨
    	public String list(Model model, @RequestParam(defaultValue="") String mode, @RequestParam(defaultValue="") String keyword) {
    		
    		HashMap<String, String> params = new HashMap<String, String>();
    		params.put("mode", mode);
    		params.put("key", keyword); //boardMapper에서 key라고 명명
    		List<Board> list = boardService.getBoardList(params);
    		model.addAttribute("list", list);
    		
    		return "board/list";
    		
    	}
    	
    	@GetMapping("writeForm")
    	public String writeForm() {
    		return "board/writeform";
    	}
    	
    	@PostMapping("write")
    	public String write(Board board, MultipartFile upload_file) {
    		
    		if(upload_file.getSize() != 0) {
    			// 파일 업로드하는 부분
    			String uploadPath = servletContext.getRealPath("/file");
    			String fileName = upload_file.getOriginalFilename();
    			String saveName = UUID.randomUUID()+"";
    			File target = new File(uploadPath, saveName);
    			if( !new File(uploadPath).exists())
    				new File(uploadPath).mkdirs();
    			try {
    				FileCopyUtils.copy(upload_file.getBytes(), target);			
    				board.setFilename(fileName);
    				board.setFileUri(target.getCanonicalPath()); // 파일의 절대 경로
    			}catch(IOException e) {
    				e.printStackTrace();
    			}
    			
    		}
    		boardService.writeBoard(board);
    		
    		return "redirect:/board/list";
    		
    	}
    	
    	@GetMapping("detail")
    	public String detail(Model model, int id) {
    		Board board = boardService.readBoard(id);
    		model.addAttribute("board", board);
    		return "board/detail";
    	}
    	
    }
    
    ```

43. src/main/java/com/hj/board/controller/FileDownLoadView.java 생성

    ```java
    package com.hj.board.controller;
    
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.OutputStream;
    import java.net.URLEncoder;
    import java.util.Map;
    
    import javax.servlet.ServletContext;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    import org.springframework.util.FileCopyUtils;
    
    public class FileDownLoadView {
    	public FileDownLoadView() {
    		setContentType("application/download; charset=UTF-8");
    	}
    	
        @Override
        protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            
           ServletContext ctx = getServletContext();
           String realPath = ctx.getRealPath("/file");
           
         
           String fileuri = (String) model.get("fileuri");// 컴퓨터 읽기 : 파일 경로 
           String filename = (String) model.get("filename"); // 사용자 응답
           // 특정 위치에서 특정 파일을 가져옴
           File file = new File(fileuri);
           
           response.setContentType(getContentType());
           response.setContentLength((int) file.length());
           
           String header = request.getHeader("User-Agent");
           boolean isIE = header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1;
           String fileName = null;
           if (isIE) { 
               fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
           } else {
               fileName = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
           }
           response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\";");
           response.setHeader("Content-Transfer-Encoding", "binary");
           
           // 응답에 대한 출력을
           OutputStream out = response.getOutputStream();
           
           FileInputStream fis = null;
           try {
               fis = new FileInputStream(file);
               
               // file에다 out을 냅다 보냄 : 사용자에게 감
               FileCopyUtils.copy(fis, out);
           } catch(Exception e){
        	   e.printStackTrace();
           } finally {
               if (fis != null) {
                   try {
                       fis.close();
                   } catch (IOException e) {
                	   e.printStackTrace();
                   }
               }
           }
           out.flush();
       }
           
               
    }
    
    ```

44. src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 수정

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans:beans xmlns="http://www.springframework.org/schema/mvc"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xmlns:beans="http://www.springframework.org/schema/beans"
    	xmlns:context="http://www.springframework.org/schema/context"
    	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
    		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
    		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    
    	<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
    	
    	<!-- Enables the Spring MVC @Controller programming model -->
    	<annotation-driven />
    
    	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
    	<resources mapping="/resources/**" location="/resources/" />
    
    	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
    	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    		<beans:property name="prefix" value="/WEB-INF/views/" />
    		<beans:property name="suffix" value=".jsp" />
    	</beans:bean>
    	
    	<context:component-scan base-package="com.hj.board.controller" />
    	
    	<beans:bean 
    	id="multipartResolver"
    	class="org.springframework.web.multipart.commons.CommonsMultipartResolver">	
    		<beans:property name="maxUploadSize" value="10485760"></beans:property>
    		<beans:property name="defaultEncoding" value="UTF-8"></beans:property>
    	</beans:bean>
    	
    	<!-- filedownload -->
    	<beans:bean class="com.hj.board.controller.FileDownLoadView" id="fileDownLoadView">
    	</beans:bean>
    	<beans:bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
    		<beans:property name="order" value="0"></beans:property>
    	</beans:bean>
    	
    	
    </beans:beans>
    
    ```

45. src/main/webapp/WEB-INF/views/board/detail.jsp 생성

    ```jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>글 상세</title>
    <%@incldue file = '/WEB-INF/views/common/header.jsp' %>
    </head>
    <body>
    	<div class="container">
    		<h2>글 상세 보기</h2>
    		<hr>
    		<div class="card" style="width:40rem;">
    			<div class="card-body">
    				<h5 class="card-title">${board.title } <span class="badge bg-danger">${board }</span></h5>
    				<div class="d-flex justify-content-between">
    					<div class="card-subtitle mb-2 text-muted">${board.writer}</div>
    					<div class="card-subtitle mb-2 text-muted">${board.regDate }</div>
    				</div>
    				<p class="card-text">${board.content }</p>
    				<hr>
    				<a href="download?id=${board.id }">${board.fileName }</a>
    				<div>
    					<a href="${root }/board/board/updateForm?id=${board.id}" class="btn btn-success">수정</a>
    					<a href="${root }/board/board/delete?id=${board.id}" class="btn btn-info">삭제</a>
    					<a href="${root }/board/list" class="btn btn-warning">목차</a>
    				</div>
    			</div>
    		</div>
    	</div>
    </body>
    </html>
    ```

46. src/main/webapp/WEB-INF/views/board/updateform.jsp 생성

    ```jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@incldue file = "/WEB-INF/views/common/header.jsp" %>
    </head>
    <body>
    	<div class="container">
    		<h2>자유게시판</h2>
    		<form method="post" action="${root }/board/update">
    			<input type="hidden" name="act" value="update"/>
    			<input type="hidden" name="id" value="${board.id }"/>
    			<div class=mb-3>
    				<label for="title" class="form-label">제목</label>
    				<input type="text" class="form-control" id="title" name="title" value="${board.title }"/>
    			</div>
    			<div class="mb-3">
    				<label for="writer" class="form-label">글쓴이</label>
    				<input type="text" class="form-control" id="writer" name="writer" value="${board.writer }"/>				
    			</div>
    			<div class="mb-3">
    				<label for="content" class="form-label">내용</label>
    				<input type="text" class="form-control" id="content" rows=7 name="content" value="${board.content }"/>				
    			</div>
    			<button class="btn btn-warning">수정</button>
    		</form>
    	</div>
    </body>
    </html>
    ```

47. src/main/webapp/WEB-INF/web.xml 수정 : 인코딩

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
    
    	<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
    	<context-param>
    		<param-name>contextConfigLocation</param-name>
    		<param-value>/WEB-INF/spring/root-context.xml</param-value>
    	</context-param>
    	
    	<!-- Creates the Spring Container shared by all Servlets and Filters -->
    	<listener>
    		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    	</listener>
    
    	<!-- Processes application requests -->
    	<servlet>
    		<servlet-name>appServlet</servlet-name>
    		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    		<init-param>
    			<param-name>contextConfigLocation</param-name>
    			<param-value>/WEsB-INF/spring/appServlet/servlet-context.xml</param-value>
    		</init-param>
    		<load-on-startup>1</load-on-startup>
    	</servlet>
    		
    	<servlet-mapping>
    		<servlet-name>appServlet</servlet-name>
    		<url-pattern>/</url-pattern>
    	</servlet-mapping>
    
    	 <filter>
            <filter-name>encoding</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <init-param>
               <param-name>encoding</param-name>
               <param-value>UTF-8</param-value>
            </init-param>
         </filter>
        
         <filter-mapping>
               <filter-name>encoding</filter-name>
               <url-pattern>/*</url-pattern>
         </filter-mapping>
    
    </web-app>
    
    ```

    