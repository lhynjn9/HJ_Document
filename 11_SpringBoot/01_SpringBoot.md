# Spring Boot

- Spring Boot 특징

  - 개발자가 직접 해야만 햇던 복잡한 설정 해결
  - 간편하고 자동화 된 빌드 및 설정을 제공
  - project에 따라 자주 사용되는 library들이 미리 조합되어 있음
  - 복잡한(XML)설정을 하지 않아도 됨
  - 내장 서버를 제공해서 WAS를 추가로 설치하지 않아도 개발과 손쉬운 배포 가능(독립 실행)
  - WAS에 배포하지 않고도 실행할 수 있는 JAR 파일로 Web Application을 개발 가능

  

- SpringBoot Project 생성

  1. https://start.spring.io/ 를 접속하여 Generate

     - Project : Maven
     - Language : Java
     - Spring Boot : 안정화 버전 중 최신버전
     - Packing : Jar
     - Java : 8
     - Dependencies 추가
       - Spring Boot DevTools
       - Spring Web
     - 다운로드 후 압축 해제
     - Import - Maven - Existing Maven Projects

  2. STS 이용

     - Spring Boot - Spring Starter Project - 1번과 동일하게 설정 - dependencies도 동일하게 설정 -  Next - Finish

     ```
     생성 중 오류 발생 시
     우클릭 - Maven - Update Project
     ```



- 프로젝트 구조
  - src/main/java : java source directory
    - [Project Name]Application.java : application을 시작할 수 있는 main method가 존재하는 스프링 구성 메인 클래스
  - src/main/resources
    - static : css, js, img 등 정적 resource directory
    - templates : SpringBoot에서 사용 가능한 여러가지 View Template(Thymeleaf, Velocity, FreeMarker 등) 위치
  - <u>**application.properties : application 및 스프링의 설정 등에서 사용할 여러가지 property를 정의한 file**</u>
    - 기존 xml 파일의 역할
    - 설정정보의 처리
  - src/main/webapp : jsp 등의 리소스 directory



- 실행 방법
  - 우클릭 - Spring Boot App 클릭
  - 오른쪽 상단의 스프링 아이콘 클릭 : Boot Dashboard
    - 프로젝트 클릭 후, 지구본 모양 클릭
      - 웹으로 연결
    - 가장 좌측 버튼 클릭 시, 서버 재구동



- 자동으로 설치되는 Bean 목록 확인(SpringBoothello2Application.java)

  ```java
  ApplicationContext context = SpringApplication.run(SpringBoothello2Application.class, args);
  		String[] names = context.getBeanDefinitionNames();
  		for(String name: names) {
  			System.out.println(name);
  		}
  ```

- src/main/webapp/index.html 생성 하여 출력해보기

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="EUC-KR">
  <title>Insert title here</title>
  </head>
  <body>
  	<h2>hello boot~</h2>
  </body>
  </html>
  ```

  

```
발생 에러 : java.lang.NoSuchMethodError: javax.servlet.http.HttpServletRequest.getHttpServletMapping()Ljavax/servlet/http/HttpServletMapping;
해결 방안 : Tomcat 9으로 재설치 후, Tomcat9의 servlet-api.jar 를 jre/lib/ext 로 복사(overwrite)
```



- #### 실습

1. src/main/java/com/hj/hello/controller/HelloController.java 생성

```java
package com.hj.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 1. 컨트롤러 등록
@Controller
public class HelloController {
	// 2. 메소드 생성
	@GetMapping("/") // 4. 어떤 요청과 매핑되는지 작성
	public String hello() {
		// 3. 넘어갈 뷰페이지 정보 전달
		return "hello";
	// /WEB-INF/views/hello.jsp로 동작
	}
}
```



2. src/main/resources/application.properties 설정

   ```properties
   # viewresolver setting
   spring.mvc.view.prefix=/WEB-INF/views/
   spring.mvc.view.suffix=.jsp
   ```

   - 참고사항 : src/main/resources/application.yaml

     - 더 간편하게 설정 가능

     ```yaml
     spring:
       mvc:
         view:
           prefix: /WEB-INF/views/
           suffix: .jsp
     ```

     

3. src/main/webapp/WEB-INF/views/hello.jsp

   ```jsp
   <%@ page language="java" contentType="text/html; charset=EUC-KR"
       pageEncoding="EUC-KR"%>
   <!DOCTYPE html>
   <html>
   <head>
   <meta charset="EUC-KR">
   <title>Insert title here</title>
   </head>
   <body>
   	<h2>hello boot + mvc</h2>
   </body>
   </html>
   ```



4. pom.xml에 jstl과 Tomcat Embed Jasper 추가

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

   