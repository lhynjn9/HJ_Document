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
- Controller(직접 생성해야 할 부분)
  - 클라이언트의 요청을 처리한 후, Model을 호출하고 그 결과를 DispatcherServlet에게 알려줌
- ModelAndView
  - Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체
- ViewResolver
  - Controller가 리턴한 뷰 이름을 기반으로 Controller의 처리 결과를 보여줄 View를 결정
- View
  - Controller의 처리 결과를 보여줄 응답 화면을 생성



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
   - DispatcherServlet이 <url-pattern>과 <servlet-name>을 확인하여       