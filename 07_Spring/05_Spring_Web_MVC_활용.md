# Spring Web MVC 활용

> #### Spring MVC 기본 세팅

- web.xml
  - 서블릿 등록(MVC)
    - 서블릿 컨테이너에서 읽힌 컨트롤러 컴포넌트 스캔 및  구현
    - web.xml에 등록된 서블릿이 사용한 servlet-context.xml에 컨트롤러부터 뷰리졸버, 빈 기타 등등 등록
  - 리스너 등록(Service 이하(Service+DAO))
    - 리스너에 의해서 루트 컨테이너가 되는 applicationContext.xml에 서비스 이하의 컴포넌트 스캔 및 구현
  - 필터 등록(한글 인코딩)
- 서비스와 컨트롤러 작성



> #### 내용 점검

- web.xml에 listener를 등록하면 applicationContext.xml을 root Container로 스프링 컨테이너를 하나 더 빌드해줌
- applicationContext.xml에는 기존에 알고있던 방법으로 빈을 등록
  - 이 안의 빈들은 모든 서블릿 컨테이너들이 의존관계 설정하는 재료로 활용 가능
  - 서비스 패키지/클래스 를 컴포넌트 스캔해서 myservice를 빈으로 등록
- servlet-context.xml(컨테이너)에서는 컨트롤러 패키지를 읽어 빈으로 등록했을 건데 mycontroller는 myservice 참조 가능
  - 왜냐면 servlet-context에서 applicationContext 참조가 가능하므로
  - MyController에서 MyService를 Autowired를 해놨더니 정상 작동



> #### Spring MVC Board

- Dynamic Web Project 생성

- Convert to Maven

  - dependencies 등록

    - spring context, spring web mvc, my batis, mysql, jstl

    - 버전 관리

      ```xml
      <!--packaging 태그 위-->
      <properties>
          <!-- 태그명은 지으면 됨 -->
          <spring-version>5.3.17</spring-version>
      </properties>
      ================================
      <dependency>
       <groupId>org.springframework</groupId>
      	    <artifactId>spring-context</artifactId>
      	    <version>${spring-version}</version>
      </dependency>
      ```

- WebContent/web.xml

  1. DispatcherServlet 등록(servlet-content.xml)

  2. lisenter 읽어오기

     - ContextLoaderListener를 등록하면, WEB-INF/applicationContext.xml을 설정 파일로써 등록

     - 다른 파일로 바꾸고 싶다면 아래와 같이 지정하면 됨

       ![캡처](https://user-images.githubusercontent.com/97647987/176862165-5b8e06d1-a9b2-4ae8-ac9c-ebdd5272fd48.JPG)

  3. encoding filter 지정

- Service 이하의 작업 처리
  - recources에 config, mapper 파일 생성
  
- src에 com.hj.board.config/model.dao/model.dto/model.service 가져오기
  - 가져온 파일을 root-container에 빈으로 등록
    - root-context.xml(applicationContext)에 등록
  - root-containter에 BoardServiceImpl을 컴포넌트 스캔하도록 만들어야 함
  
- root-context.xml 생성
  - namespace에서 context 추가 하고, 컴포넌트 스캔(서비스 패키지 읽어오기)
  
- web.xml(DispatcherServlet)
  - 컨테이너를 설정파일(servlet-context.xml)을 만들어주고 컨트롤러를 만들고 컨트롤러를 빈으로 등록(component-scan)하고 실제 컨트롤러 구현
  
- 컨트롤러 등록
  - @Controller
  
- jsp를 저장할 폴더 생성
  - WEB-INF/views
    - list -> 게시물 가지고 list.jsp로 이동
    - detail -> 게시물 id를 받아서 조회하고 detail.jsp로 이동
    - wrtieform -> writeform.jsp이동
    - write -> 게시물 받아서 DB에 넣고 list로 redirect
    - updateform -> 게시물 id 받아서 정보를 가지고 updateform.jsp로 이동
    - update -> 게시물 받아서 DB를 수정하고 id 들고 detail로 redirct
    - delete -> 게시물 id받아서 DB를 삭제하고 list로 redirect