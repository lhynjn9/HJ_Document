# RDBMS를 이용한 Import/Export

- 사용 RDBMS : MySQL

- DB 계정 등록

  1. 계정 생성

     ```mysql
     create user 'userid'@'localhost' identified with mysql_native_password by 'password';
     ```

  2. 권한 부여

     ```mysql
     -- userid에게 모든 DB의 모든 테이블에 모든 권한 부여
     grant all privileges on *.* to 'userid'@'localhost';
     ```

  3. 권한 적용

     ```mysql
     flush privileges
     ```

     

- MySQL 사용하기

  1. 데이터베이스 생성

     ```mysql
     create database [databasename];
     ```

  2. 데이터베이스 사용

     ```mysql
     use [databasename];
     ```

  3. 데이터베이스 목록 조회

     ```mysql
     show databases;
     ```

     

- Data Import/Export

  - Import시, 발생할 수 있는 오류
    - 인코딩 문제 : 저장 할 때, uft-8인지 확인
    - 확장자가 대문자로 입력되어 있을 경우 오류가 발생할 수 있음
    - 데이터 타입이 맞지 않아 오류가 발생할 수 있음