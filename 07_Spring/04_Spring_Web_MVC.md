# Spring Web MVC

- Controller의 처리



> #### 알아두면 좋은 사전지식

- preSpringMVC
  - filter : 어떤 요청이 서블릿으로 가기 전 수행해야 할 작업
  - listener : 상태(이벤트)의 변화에 반응하는 작업
    - source-contextinitialized
    - ServletContext : 웹 어플리케이션이 정상적으로 시작될때, 종료될때를 처리할 수 있는 인터페이스
    - contextInitialized : 서블릿이 시작할때 이벤트를 캐치하는 것



> #### Spring MVC

- Spring은 DI나 AOP 같은 기능 뿐만 아니라, Servlet 기반의 WEB 개발을 위한 MVC Framework를 제공
- Model2 Architecture와 Front Controller Pattern을 Framework 차원에서 제공
- Spring을 기반으로 하고 있기 때문에 Spring이 제공한는 Transaction 처리나 DI 및 AOP 등을 손쉽게 사용
- DispatcherSErvlet(FrontController)를 중심으로 디자인되었으며, ViewResolver, Handler, Mapping, Controller와 같은 객체와 함께 요청을 처리하도록 구성되어 있음



> #### Spring MVC 구성요소

- DispatcherServlet(Front Controller 역할)
  - 요청 처리를 위한 기능을 제공
  - <u>모든 클라이언트의 요청 처리</u>
  - Controller에게 클라이언트의 요청을 전달하고, Controller가 리턴 한 결과 값을 View에게 전달하여 알맞은 응답을 생성
- HandlerMapping
  - <u>클라이언트의 요청 URL을 어떤 Controller가 처리할지를 결정</u>
  - URL과 요청 정보를 기준으로 <u>어떤 핸들러 객체(Controller)를 사용할지 결정하는 객체</u>이며, DispatcherServlet은 하나 이상의 핸들러 매핑을 가질 수 있음
- Controller(직접 생성해야 할 부분)(.java)
  - <u>요청에 따라 수행할 메서드를 선언하고, 요청처리를 위한 로직 수행(비즈니스 로직 호출)</u>
  - 클라이언트의 요청을 처리한 후, Model을 호출하고 그 결과를 DispatcherServlet에게 알려줌
- ModelAndView
  - <u>요청 처리를 하기 위해서 필요한 혹은 그 결과를 저장하기 위한 객체</u>
  - Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체(index)

- ViewResolver
  - <u>Controller에 선언된 view이름 기반으로 결과를 반환할 view를 결정</u>
  - Controller가 리턴한 뷰 이름을 기반으로 Controller의 처리 결과를 보여줄 View를 결정(.jsp)

- View
  - Controller의 처리 결과를 보여줄 <u>응답 화면</u>을 생성(index.jsp)



> #### Spring MVC 요청 흐름

![캡처](https://user-images.githubusercontent.com/97647987/175990267-31bf69ca-d7a9-450e-a84b-a62c616b63a4.JPG)

![캡처](https://user-images.githubusercontent.com/97647987/174886396-e66a3715-6b1e-47b1-993c-594079c69cec.JPG)





> #### Spring MVC 실행 순서

![캡처](https://user-images.githubusercontent.com/97647987/174886587-390943b6-01ef-416e-b9e1-45cacd0022fb.JPG)



1. DispatcherServlet이 요청을 수신
   - 단일 Front Controller Servlet
   - 요청을 수신하여 처리를 다른 컴포넌트에 위임
   - 어느 Controller에 요청을 전송할지 결정
2. DispatcherServlet은 Handler Mapping에 어느 Controller를 사용할 것인지 문의
   - HandlerMapping : 실제 요청을 처리할 담당자
   - URL과 Mapping
3. DispatcherServlet은 요청을 Controller에게 전송하고  Controller는 요청을 처리한 후 결과 리턴
   - Business Logic 수행 후 결과 정보(Model)가 생성되어 JSP와 같은 view에서 사용됨
4. 결과(요청 처리를 위한 data, 결과를 보여줄 view의 이름)를 ModelAndView에 담아 반환
5. ModelAndView Object에 수행결과가 포함되어 DispatcherServlet에 리턴
6. ModelAndView는 실제JSP 정보를 갖고 있지 않으며, ViewResolver가 논리적 이름을 실제 JSP 이름으로 변환
7. 결과를 처리할 View에 ModelAndView를 전달
8. DispatcherServlet은 View가 만들어낸 결과를 응답



> #### Spring MVC 구성하기

- SpringMVC1

0. pom.xml를 생성하고 라이브러리 세팅

1. DispatherServlet 생성

   - <u>web.xml(pjt/src/main/webapp/WEB-INF/web.xml) 파일에 DispatcherServlet 등록(Servlet을 bean으로 등록) 및 Spring 파일 설정 등록</u>

   - load-on-startup : 시작할 때(컨테이너가 만들어질 때) 생성하라는 의미(선택적 옵션)

   - DispatcherServlet 역할

     - **<u>Front Controller, Spring Container(for mvc : handler mapping, view resolver, controller와 같은 것들을 등록해서 사용)</u>**
     - Spring Container가 되기 위해서 **<u>스프링 설정 파일</u>**이 필요
       - **<u>[servlet name]-servlet.xml이라는 설정 파일</u>**을 만들면 이 설정파일을 이용해서 컨테이너가 만들어짐
       - 이 경로를 직접 지정해주는 방법이 <init-param>의 <param->
     
     ![캡처](https://user-images.githubusercontent.com/97647987/176826993-19879ebb-edf3-4259-a9a5-7260bca62226.JPG)

2. 위에서 지정한 경로에 Spring Bean Configuration File -> 이름 : servlet-context (컨테이너) 생성

   - Controller 생성

     - handlermapping으로 호출할 함수들을 구분(annotation 이용)

     - 다양한 Annotation 

       ```java
       @RequestMapping("home") //8080/SpringMVC1/home
       @GetMapping("abc") // get방식에만 동작하는 requestMapping
       @PostMapping("abc") //post 방식에만 동작하는 requestMapping
       ```

     - Controller의 반환 유형

       - ModelAndView
       - String(attribute가 있는 버전, 없는 버전)

       ![캡처](https://user-images.githubusercontent.com/97647987/176230762-f3ea836f-c945-4c3d-ba0a-9943cf3f55f9.JPG)

     - Controller 매개변수

       ![캡처](https://user-images.githubusercontent.com/97647987/176231047-8f2ba982-162e-4173-9c4d-2dc296543690.JPG)

       ![캡처](https://user-images.githubusercontent.com/97647987/176178506-8e34358b-2991-4f54-9079-945772226038.JPG)

       ![캡처](https://user-images.githubusercontent.com/97647987/176227261-5ec0380a-ef18-4785-9025-8142dc2822df.JPG)

       ![캡처](https://user-images.githubusercontent.com/97647987/176227516-43bcab45-ddb3-485b-93cc-b718dc0ce267.JPG)
       
       ![캡처](https://user-images.githubusercontent.com/97647987/176228488-506ac6c9-4dbb-46fe-bda6-51086f56e810.JPG)

   - servlet-context에 등록(bean으로 Controller 등록)

     - <u>servlet-context.xml에 `@Component`가 있는 클래스는 모두 빈으로 등록하라는 명령 작성</u>
       - <u>하단탭에서 Namespaces에서 추가적인 태그 선택 가능 : context 추가(다른 공간에 있는 것을 활용하기 위한 설정)</u>
     - <context:component-scan>으로 등록

3. jsp 파일 생성(WebContent/home.jsp, WebContent/home.jsp)

4. 실행 후 , url에 `/home`을 작성하여 실행 확인 가능

4. root WebApplicationContext

- 여러 Servlet에서 공유해야하는 DAO, Service 등의 bean을 포함

- ContextLoaderListener를 Controller에 지정을 할 경우, 해당 스프링 설정파일(root-context.xml)을 매개로 삼아서 root container를 빌드하게 해줌

  - servletContextLisenter를 implement 해서 servlet이 initialized 될 때, 설정 파일(root-context)를 읽어서 스프링 컨테이너를 빌드하고 그 컨테이너가 루트 컨테이너가 되도록 작업 => `<listener>`
  
  ![캡처](https://user-images.githubusercontent.com/97647987/176862165-5b8e06d1-a9b2-4ae8-ac9c-ebdd5272fd48.JPG)

6. 인코딩 문제

- filter로 해결

  - web.xml에 filter 등록 : spring에서 제공하고 있음

    ```xml
    	<filter>
    		<!-- 이름은 임의로 설정 -->
    		<filter-name>encodingFilter</filter-name>
    		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    		<!-- 설정자의 이름은 encoding이고 거기에 UTF-8을 넣어줘 -->
    		<init-param>
    			<param-name>encoding</param-name>
    			<param-value>UTF-8</param-value>
    		</init-param>
    	</filter>
    	<filter-mapping>
    		<filter-name>encodingFilter</filter-name>
    		<!-- /로 본 필터가 모든 url을 처리 -->
    		<url-pattern>/*</url-pattern>
    	</filter-mapping>
    ```

    



1. sevlet-context.xml

   - MVC bean 구성요소 등록(handler mappint, view resolver, controller)

   - 설정 파일에 HandlerMapping 설정

     ![캡처](https://user-images.githubusercontent.com/97647987/176178130-04055696-98c1-4105-83bd-462706ccaf95.JPG)

2. Controller 구현 및 Controller와 JSP의 연결을 위해 View Resolver 설정

3. JSP(View)작성

   ![캡처](https://user-images.githubusercontent.com/97647987/176178748-54682c70-ef21-4800-aff3-505de6d16e74.JPG)

> ####  Spring MVC 구현 로직

1. web.xml - DispatcherServlet 설정
   - web.xml을 읽음 -> 내부에 DispatcherServlet이 설정되어 있음 -> Tomcat 메모리에 올림 -> 올라감과 동시에 servlet-context에 설정한 정보(web 관련 설정/Controller에 대한 정보가 있음)를 읽음
   - web.xml의 <url-pattern>과 <servlet-name>을 확인하여  <servlet>의 <servlet-name>을 확인하여 DistpatcherServlet을 확인
     - <url-pattern>은 DispatcherServlet이 처리하는 URL Mapping pattern을 정의
     - <u>servlet-context.xml 파일 확인</u>
   - web.xml의 <servlet>의 <init-param> : DispatcherServlet을 Tomcat 메모리에 만들고 그 안에 servlet-context.xml 설정을 넣으라는 파라미터
     - <init-param>부분에서 재정의 발생
     - 미설정 시, <servlet-name>-servlet.xml file에서 applicationContext 정보를 로드
     - <context-param> : 프로젝트가 WAS에 올라가는 순간 root-context.xml을 읽어서 contextConfigLocation이라는 setter를 호출
     - <listener> : context가 로딩이 되는 순간에 감시하다가 <context-param>을 읽음
   - Spring Container는 설정 파일의 내용을 읽고 ApplicationContext 객체를 생성
   - Servlet이므로 1개 이상의 DispatcherServlet 설정 가능
   - <load-on-startup>설정 시, WAS startup 초기화 작업 진행
2. Controller Class(.java) 작성
   - 종속적이지 않은 일반 클래스
   - servlet-context.xml에 component-scan으로 등록하고 Annotation으로 등록
3. Controller와 reponse page 연결을 위한 ViewResolver 설정(servlet-context.xml)



> #### Controller

- @RequestMapping

  - URL을 클래스 또는 특정 핸들러(메서드)에 매핑

  - 일반적 클래스에 작성하는 @RequestMapping은 요청 경로 혹은 요청 경로, 혹은 요청 패턴에 매칭

  - 메서드 Annotation은 요청 방식(GET, POST) 등ㅇ로 범위를 좁혀줌


  ![캡처](https://user-images.githubusercontent.com/97647987/176226773-889a3c52-7d99-4851-84fc-2f461b426dad.JPG)

  ![캡처](https://user-images.githubusercontent.com/97647987/176226845-fac9690a-eebb-46cf-86e2-87e24d967e43.JPG)

  

![캡처](https://user-images.githubusercontent.com/97647987/176227004-e63e3464-7037-41b4-a5e0-67510be35975.JPG)






![캡처](https://user-images.githubusercontent.com/97647987/176228596-6d449f30-e5cb-40c2-9ee7-0b13c712c312.JPG)





![캡처](https://user-images.githubusercontent.com/97647987/176231129-4585448e-f080-4b4b-8618-f939c59f98ba.JPG)![캡처](https://user-images.githubusercontent.com/97647987/176233675-c4196212-3308-4b4e-ae18-fb1cc43a3a8d.JPG)
