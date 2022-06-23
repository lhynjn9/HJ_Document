# IoC&Container

> #### IoC(Inversion of Control, 제어의 역행)

- <u>객체 제어 방식 : 객체 생성을 Container에게 위임하여 처리</u>

- 객체지향 언어에서 Object 간의 연결 관계를 런타임에 결정

- 객체 간의 관계가 느슨하게 연결됨 = 객체 간의 결합도를 떨어뜨릴 수 있음

  - 다형성, Factory, <u>Assembler</u>, 로 결합도를 낮춤
    - Assembler : 실행 시점에 클래스 간의 관계가 형성이 됨 = Spring Container

- <u>IoC의 구현 방법 중 하나가 DI</u>

  

> #### IoC 유형

- Dependency Lookup
  - 컨테이너가 lookup context를 통해서 필요한 Resource나 Object를 얻는 방식
  - JNDI 이외의 방법을 사용한다면 JNDI 관련 코드를 오브젝트 내에서 일일이 변경해주어야 함
  - Lookup한 Object를 필요한 타입으로 Casting 해주어야 함
  - Naming Exception을 처리하기 위한 로직이 필요
- Dependency Injecton
  - Object에 Lookup 코드를 사용하지 않고 컨테이너가 직접 의존 구조를 Object에 설정할 수 있도록 지정해주는 방식
  - Object가 컨테이너의 존재 여부를 알 필요가 없음
  - Lookup 관련된 코드들이 Object 내에서 사라짐
  - Setter Injection과 Constructor Injection, Method Injection이 있음



> #### Containter

- <u>객체의 생성, 사용, 소멸에 해당하는 라이프사이클을 담당</u>
- 라이프사이클을 기본으로 애플리케이션 사용에 필요나 주요 기능을 제공
- 기능
  - 라이프사이클 관리, Dependency 객체 제공, Thread 관리, 기타 애플리케이션 실행에 필요한 환경
- 필요성
  - 비즈니스 로직 외에 부가적인 기능들에 대해서는 독립적으로 관리되도록 하기 위함
  - 서비스 lookup이나 Configuration에 대한 일관성을 갖기 위함
  - 서비스 객체를 사용하기 위해 각각 Factory 또는 Singleton 패턴을 직접 구현하지 않아도 됨



> #### IoC Container(= Spring Container)

- 오브젝트의 생성과 관계 설정, 사용, 제거 등의 작업을 애플리케이션 코드 대신 독립된 컨테이너가 담당
- 컨테이너가 코드 대신 오브젝트에 대한 제어권을 갖고 있어 IoC라고 부름
- 스프링에서 IoC를 담당하는 컨테이너에는 BeanFactory, ApplicationContext가 있음



> #### Maven : 버전/라이브러리 관리

- Configure - Convert to Maven Project

  - pom.xml 파일 생성

  - [여기](https://mvnrepository.com/)에서 spring context(5.3.18)의 Maven을 복사

    ```xml
      </build> 
    <!-- 여기 아래 넣기 -->
      <dependencies>
      	<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
    	<dependency>
    	    <groupId>org.springframework</groupId>
    	    <artifactId>spring-context</artifactId> <!-- 내부적으로 필요한 것을 알아서 다운을 같이 함 : Maven의 역할 --> 
    	    <version>5.3.18</version>
    	</dependency>
      </dependencies>
    ```



> #### ApplicationContext 사용을 위한 준비

- New - Spring Bean Configuration FIle : Spring 설정을 위한 xml 파일 - beans 선택



- Spring VS Spring Boot
  - Spring Boot는 Spring 내부 문법을 사용
  - Spring Legacry Project는 기존의 Spring 방식
