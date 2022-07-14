# MyBatis

- SQL 매핑 프레임워크

  - SQL문과 저장 프로시저 등의 매핑을 지원하는 퍼시스텀스 프레임워크(persistence framework)

- JDBC로 처리하는 상당부분의 코드(커넥션 관리, 예외 처리 등)와 파라미터 설정 및 결과 처리를 대신해줌

- Map인터페이스 그리고 자바 POJO를 설정 데이터베이스와 매핑해서 사용할 수 있음, XML과 Annotation 설정을 통해 사용 가능 

- 구성

  - 환경설정 파일

    - Configuration.xml
    - MyBatis 전반에 걸친 세팅
    - DB접속 정보, 모델 클래스 정보, 매핑 정보

  - Mapper

    - 사용한 SQL문 정의

  - Mapped Statement

    - SqlSession 빌드 및 사용

      - SqlSessionFactory

        - 모든 마이바티스 애플리케이션은 SqlSessionFactory 인스턴스를 사용

        - SqlSessionFactory는 SqlSession을 만듦

          ![캡처](https://user-images.githubusercontent.com/97647987/177158659-806c6208-eb8e-4343-a0fb-d8b82211ddac.JPG)

      - SqlSession

        - 데이터베이스에 대해 SQL 명령어를 실행하기 위한 메서드 포함

    - SQL문 실행

  - Input/Ouput

    - SQL 실행 시 필요한 데이터
    - SQL 실행 결과



> #### Spring Mybatis 연동

- ver 1

  - spring에서 sqlsessionfactory를 빈으로 등록

  ```xml
  <!-- SqlSessionFactory를 빈으로 등록 -->
  		<bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactory">
  			<property name="dataSource" ref="dataSource"></property>
  			<property name="typeAliasesPackage" value="com.hj.jdbc.model.dto"></property>
  			<property name="mapperLocations" value="classpath:/mapper/studentMapper.xml"></property>
  		</bean>
  ```

  - Dao에 주입하여 활용

-  ver 2

  - spring은 객체관리전문가이기 때문에 thread 안전한 SqlSession객체를 만들어줌, 이것을 주입받아 사용해도 됨

    ```xml
    <!-- sql 세션이 생김 -->
    		<bean class="org.mybatis.spring.SqlSessionTemplate">
    			<constructor-arg ref="sqlSessionFactory"></constructor-arg>
    		</bean>
    ```

    

- ver3

  - 매퍼 주입

    ```xml
    <bean class="org.mybatis.spring.mapper.MapperFactoryBean">
    			<property name="sqlSessionTemplate" ref="sqlSession"></property>
    			<property name="mapperInterface" value="com.hj.jdbc.model.dao.StudentDao"></property>
    		</bean>B
    ```

    

  

