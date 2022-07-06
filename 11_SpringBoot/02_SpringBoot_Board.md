# Spring Boot Board 

1. Dependencies 추가

   - Spring Boot DevTools

   - Spring Web

   - MyBatis Framework

   - Spring Web

2. 우클릭 - Gerneral - File system - src/main 선택 - finish : 기존 프로젝트 추가

   - pom.xml에 jstl과 Tomcat Embed Jasper 추가

     - version 삭제 해도 가능
       - maven의 특징 : 공통으로 사용되는 것들에 대해 참조해서 상속받은 것처럼 사용 가능

     ```xml
     <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
     		<dependency>
     		    <groupId>javax.servlet</groupId>
     		    <artifactId>jstl</artifactId>
     		</dependency>
     		
     		<!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper -->
     		<dependency>
     		    <groupId>org.apache.tomcat.embed</groupId>
     		    <artifactId>tomcat-embed-jasper</artifactId>
     		</dependency>
     ```


3. src/main/resources/application.properties 설정

```properties
# viewresolver setting
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

4. src/main/webapp/WEB-INF/web.xml 삭제

5. src/main/webapp/WEB-INF/spring/root-context.xml 삭제

6. src/main/java/com/hj/board/configuration/DBConfig.java 생성

   ```java
   package com.hj.board.configuration;
   
   import org.mybatis.spring.annotation.MapperScan;
   import org.springframework.context.annotation.Configuration;
   
   // 데이터베이스 연관 환경 설정
   @Configuration
   @MapperScan(basePackages="com.hj.board.model.dao")
   public class DBConfig {
   
   }
   ```

7. src/main/java/com/hj/board/configuration/WebConfig.java 생성

   ```java
   package com.hj.board.configuration;
   
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.servlet.ViewResolver;
   import org.springframework.web.servlet.config.annotation.EnableWebMvc;
   import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
   import org.springframework.web.servlet.view.BeanNameViewResolver;
   import org.springframework.web.servlet.view.InternalResourceViewResolver;
   
   @Configuration
   @EnableWebMvc // 현재 설정하는 것으로 mvc를 사용하겠다는 의미
   public class WebConfig implements WebMvcConfigurer{
   	// 환경 설정 파일에 있는 변수 값을 이용
   	@Value("${spring.mvc.view.prefix}")
   	private String prefix;
   	@Value("${spring.mvc.view.suffix}")
   	private String suffix;
   	
   	
   	// 직접 viewresolver 등록
   	@Bean
   	public ViewResolver internalViewResolver() {
   		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
   		resolver.setPrefix(prefix);
   		resolver.setSuffix(suffix);
   		return resolver;
   	}
   	// 직접 viewresolver 등록
   	@Bean
   	public ViewResolver beanViewResolver() {
   		BeanNameViewResolver resolver = new  BeanNameViewResolver();
   		resolver.setOrder(0);
   		return resolver;
   		
   	}
   }
   
   ```

   