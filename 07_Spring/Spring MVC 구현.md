# Spring MVC 구현

1. Spring Legacy Project - Spring MVC Project - Next - com.000.hello 작성(<u>hello : 애플리케이션 이름</u>)
2. 폴더 구조
   - main : 실제 Java
   - test : junit(sprint test)
   - src/main/webapp : web-content
   - src/main/webapp/WEB-INF/spring : 설정
3. pom.xml에서 버전 설정
   - java : 1.8
   - spring : 5.3.18
   - maven-compiler-plugin : 1.8
   - 적용 : Maven - Update Project - Force update - OK
4. webapp - New - Other - jsp 생성
   - 실행 시, url : localhost:8080/<u>hello</u>/index.jsp

​	=> 여기까지는 jsp를 직접 접근함, MVC에서는 Controller를 이용하기로 함

5. index.jsp를 WEB-INF/로 이동
   - 실행 불가 : WEB-INF는 웹에서 직접 접근 불가한 위치이므로
6. index.jsp를 WEB-INF/views/로 이동
7. web.xml
   - 어떤 것이 설정 되어 있는지 확인
8. Project를 실행시키면 오류가 발생
   - home.jsp가 없어서 발생 : index.jsp로 변경함
   - HomeController.java의 return 값이 뷰의 이름 : home -> index로 변경
     - 실행 시, url : localhost:8080/<u>hello</u>/
9. HomeController.java
   - model.addAttribute("serverTime", formattedDate ); => model.addAttribute("msg", "안녕 스프링이야 컨트롤러 당겨왔어" ); 로 변경
10. index.jsp
    - ${msg}

11. Project 실행
    - Controller을 이용한 실행 확인