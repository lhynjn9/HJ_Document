# Spring Back rest

- 환경설정

  - Maven
  - Packing : Jar
  - Java Version : 8
  - Dependencies : Lombok, Mybatis Framework, MySQL Driver, Spring Boot DevTools, Spring Web

- MySQL

  ```mysql
  drop database if exists hj_borad;
  create database hj_board default CHARACTER set utf8mb4;
  
  use hj_board;
  
  DROP TABLE board;
  
  create table board(
  	id int AUTO_INCREMENT,
  	writer VARCHAR(30) NOT NULL,
      title VARCHAR(200) NOT NULL,
      content TEXT,
      view_cnt INT DEFAULT 0,
      reg_date TIMESTAMP DEFAULT now(),
      PRIMARY KEY (id)
  );
  
  INSERT INTO board (title, writer, content)
  VALUES ('안녕하세요', '이현정', '반갑습니다'),
  ('DB', '현정이', 'ez..?'),
  ('벌써금용일?', '김철수', '월화수목금토일');
  
  select * from board;
  
  SELECT DATe_FORMAT(reg_date, '%Y-%m-%d %H:%i:%s') as reg_date
  FROM board;
  ```

  

  