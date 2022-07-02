# Spring_DI_Board 예제

- SpringDI
- DAO : 데이터베이스와의 상호 작용
  - 데이터를 추가하고 삽입하고 삭제하는 등의 관점
- Service : 기능에 대한 영역(읽고 쓰고 조회 등의 관점)



> #### Spring DI Board

- <u>dao, dto, service 작성</u>
- pom.xml : 스프링 설정 파일 작성
- application Context : BoardDaoImpl과 BoardServiceImpl을 빈으로 등록
  - Component-scan 작성
  - Annotation 작성
  - Service 설정자에는 의존관계를 위해 Autowired 작성
- 확인을 위한 컨테이너 빌드
  - com/hj/board/Test.java
  - applicatoinContext 빌드 : 객체 공장
  - 필요한 객체 회수
  - 사용



> #### Spring에서 각 계층을 구분하기 위한 Annotation

- Component를 상속받아 만들어진 Annotation
  - `@Service` : Service
  - `@Repository` : DAO
  - `@Controller`



> #### sql구문 사용

- MyAppSQLConfig.getSession.getMapper(Dao.class)
  - getMapper : 이름이 같은 sql 구문을 호출, 하나의 BoardDaoImpl
  - getMapper를 BoardServiceImpl의 설정자에 넣어주면 sql구문을 호출할 수 있음
    - com.hj.board.config에 MyAppSqlConfig.java 작성
    - pom.xml에 mybatis, mysql 라이브러리 추가
    - resources/에 db.properties, mybatis-config.xml, board.xml 생성
    - BoardServieImpl에 직접 주입 => Spring으로 직접 주입으로 변경할 것임