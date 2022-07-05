# Spring JDBC

- pom.xml

  - mysql, spring context, spring jdbc(spring context와 동일 버전)

- 스프링 설정 파일 생성

  - resources/applicationContext.xml

  - 데이터베이스 연결

    ```xml
    <bean class="org.springframework.jdbc.datasource.DriverManagerDataSource"
    	id = "dataSource">
    		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
    		<property name="url" value="jdbc:mysql://localhost:3306/hj_board?ServerTimezone=UTC"></property>
    		<property name="username" value="root"></property>
    		<property name="password" value="0000"></property>
    	</bean>
    	
    	<!-- Connetion 역할을 해주지만, 더 유용한 것 -->
    	<bean class="org.springframework.jdbc.core.JdbcTemplate">
    		<property name="dataSource" ref="dataSource"></property>
    	</bean>
    ```

