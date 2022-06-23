# Spring Web MVC

- Controller의 처리



> #### Spring MVC 특징

- Spring은 DI나 AOP 같은 기능 뿐만 아니라, Servlet 기반의 WEB 개발을 위한 MVC Framework를 제공
- Model2 Architecture와 Front Controller Pattern을 Framework 차원에서 제공
- Spring을 기반으로 하고 있기 때문에 Spring이 제공한는 Transaction 처리나 DI 및 AOP 등을 손쉽게 사용



> #### Spring MVC 구성요소

- DispatcherServlet(Front Controller 역할)
  - 모든 클라이언트의 요청을 전달 받은
  - Controller에게 클라이언트의 요청을 전달하고, Controller가 리턴 한 결과 값을 View에게 전달하여 알맞은 응답을 생성
- HandlerMapping
  - 클라이언트의 요청 URL을 어떤 Controller가 처리할지를 결정
  - URL과 요청 정보를 기준으로 <u>어떤 핸들러 객체(Controller)를 사용할지 결정하는 객체</u>이며, DispatcherServlet은 하나 이상의 핸들러 매핑을 가질 수 있음
- Controller(직접 생성해야 할 부분)(.java)
  - 클라이언트의 요청을 처리한 후, Model을 호출하고 그 결과를 DispatcherServlet에게 알려줌
- ModelAndView
  - Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체(index)
- ViewResolver
  - Controller가 리턴한 뷰 이름을 기반으로 Controller의 처리 결과를 보여줄 View를 결정(.jsp)
- View
  - Controller의 처리 결과를 보여줄 응답 화면을 생성(index.jsp)



> #### Spring MVC 요청 흐름

![캡처](https://user-images.githubusercontent.com/97647987/174886396-e66a3715-6b1e-47b1-993c-594079c69cec.JPG)





> #### Spring MVC 실행 순서

![캡처](https://user-images.githubusercontent.com/97647987/174886587-390943b6-01ef-416e-b9e1-45cacd0022fb.JPG)



1. DispatcherServlet이 요청을 수신
   - 단일 Front Controller Servlet
   - 요청을 수신하여 처리를 다른 컴포넌트에 위임
   - 어느 Controller에 요청을 전송할지 결정
2. DispatcherServlet은 Handler Mapping에 어느 Controller를 사용할 것인지 문의
   - URL과 Mapping
3. DispatcherServlet은 요청을 Controller에게 전송하고  Controller는 요청을 처리한 후 결과 리턴
   - Business Logic 수행 후 결과 정보(Model)가 생성되어 JSP와 같은 view에서 사용됨
4. ModelAndView Object에 수행결과가 포함되어 DispatcherServlet에 리턴
5. ModelAndView는실제JSP 정보를 갖고 있지 않으며, ViewResolver가 논리적 이름을 실제 JSP 이름으로 변환
6. View는 결과 정보를 사용하여 화면을 표현





> #### Spring MVC 구현 단계

1. web.xml 파일에 DispatcherServlet 등록 및 Spring 파일 설정 등록
2. 설정 파일에 HandlerMapping 설정
3. Controller 구현 및 Context 파일 설정(servlet-context.xml 등록)
4. Controller와 JSP의 연결을 위해 View Resolver 설정
5. JSP 코드 작성



> ####  Spring MVC 구현

1. web.xml - DispatcherServlet 설정
   - web.xml을 읽음 -> 내부에 DispatcherServlet이 설정되어 있음 -> Tomcat 메모리에 올림 -> 올라감과 동시에 servlet-context에 설정한 정보(web 관련 설정/Controller에 대한 정보가 있음)를 읽음
   - web.xml의 <url-pattern>과 <servlet-name>을 확인하여  <servlet>의 <servlet-name>을 확인하여 DistpatcherServlet을 확인
     - <url-pattern>은 DispatcherServlet이 처리하는 URL Mapping pattern을 정의
     - servlet-context.xml 파일 확인
   - web.xml의 <servlet>의 <init-param> : DispatcherServlet을 Tomcat 메모리에 만들고 그 안에 servlet-context.xml 설정을 넣으라는 파라미터
     - <init-param>부분에서 재정의 발생
     - 미설정 시, <servlet-name>-servlet.xml file에서 applicationContext 정보를 로드
     - <context-param> : 프로젝트가 WAS에 올라가는 순간 root-context.xml을 읽어서 contextConfigLocation이라는 setter를 호출
     - <lisner> : context가 로딩이 되는 순간에 감시하다가 <context-param>을 읽음
   - Spring Container는 설정 파일의 내용을 읽고 ApplicationContext 객체를 생성
   - Servlet이므로 1개 이상의 DispatcherServlet 설정 가능
   - <load-on-startup>설정 시, WAS startup 초기화 작업 진행

2. Controller Class(.java) 작성
   - 종속적이지 않은 일반 클래스
   - servlet-context.xml에 component-scan으로 등록하고 Annotation으로 등록

3. Controller와 reponse page 연결을 위한 ViewResolver 설정(servlet-context.xml)



