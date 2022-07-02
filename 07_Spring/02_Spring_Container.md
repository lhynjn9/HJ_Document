# Spring Container

- 예시 코드 : SpringDI5



> #### Containter

- <u>객체의 생성, 사용, 소멸에 해당하는 라이프사이클을 담당</u>
- 스프링에서 핵심적인 역할을 하는 객체를 Bean이라고 하며, Container는 Bean의 인스턴스화 조립, 관리의 역할, 사용 소멸에 대한 처리를 담당
- 라이프사이클을 기본으로 애플리케이션 사용에 필요나 주요 기능을 제공
- 기능
  - 라이프사이클 관리, Dependency 객체 제공, Thread 관리, 기타 애플리케이션 실행에 필요한 환경
- 필요성
  - 비즈니스 로직 외에 부가적인 기능들에 대해서는 독립적으로 관리되도록 하기 위함
  - 서비스 lookup이나 Configuration에 대한 일관성을 갖기 위함
  - 서비스 객체를 사용하기 위해 각각 Factory 또는 Singleton 패턴을 직접 구현하지 않아도 됨



> #### IoC Container(= Spring Container)

- <u>**객체 관리 컨테이너**</u>
- 오브젝트의 생성과 관계 설정, 사용, 제거 등의 작업을 애플리케이션 코드 대신 독립된 컨테이너가 담당
- 컨테이너가 코드 대신 오브젝트에 대한 제어권을 갖고 있어 IoC라고 부름
- 스프링에서 IoC를 담당하는 컨테이너에는 BeanFactory, ApplicationContext가 있음



> #### Spring DI Container

- Spring DI Container가 관리하는 객체를 빈(Bean)이라 하고, 이 빈들의 생명주기를 관리하는 의미로 BeanFactory라 함
- **BeanFactory에 여러가지 컨테이너 기능을 추가하여 ApplicationContext라 함**
  - BeanFactory : 빈을 등록/생성/조회/반환, getBean( ) 메소드가 정의되어 있음
  - ApplicationContext : BeanFactory를 확장한 인터페이스, BeanFactory보다 널리 쓰인다.빈을 등록/생성/조회/반환하는 기능 뿐만 아니라, 각종 부가서비스를 추가적으로 제공 



> #### Spring DI 용어 정리

- 빈(Bean)
  - 스프링이 IoC방식으로 관리하는 오브젝트
  - 스프링이 직접 생성와 제어를 담당하는 오브젝트만을 Bean이라고 부름
- 빈 팩토리(BeanFactory)
  - 스프링이 IoC를 담당하는 핵심 컨테이너로, Bean을 등록, 생성, 조회, 반환하는 기능을 담당
  - 일반적으로 BeanFactory를 바로 사용하지 않고 이를 확장한 ApplicationContext를 이용
- 애플리케이션  컨텍스트(ApplicationContext)
  - BeanFactory를 확장한 IoC컨테이너로, Bean을 등록하고 관리하는 기본적인 기능은 BeanFactory와 동일
  - 스프링이 제공하는 각종 부가서비스를 추가로 제공
  - BeanFactory라고 부를 때에는 주로 빈의 생성과 제어의 관점에서 이야기하는 것이고, Application Context라고 할 때는 스프링이 제공하는 애플리케이션 지원기능을 모두 포함해서 이야기 하는것으로 간주
- 웹애플리케이션 컨텍스트(WebApplicationContext)
  - 웹 환경에서 Spring을 사용하기 위한 기능이 추가됨
  - 대표적인 구현 클래스로 XmlWebApplicationContext가 있음

- 설정정보/설정 메타정보(configuration metadata)
  - 스프링의 설정정보란 ApplicationContext 또는 BeanFactory가 IoC를 적용하기 위해 사용하는 메타정보를 말함
  - 설정정보는 IoC컨테이너에 의해 관리되는 Bean객체를 생성하고 구성할때 사용
  - 작성 방식 : XML, Annotation, Java방식
- 스프링 프레임워크(Spring Framework)
  -  IoC컨테이너, Application Context를 포함해서 스프링이 제공하는 모든기능을 통틀어 말할 때 주로 사용



> #### 빈 등록 방법

- XML

  - xml 문서 형태로  빈의 설정 메타 정보 기술
  - 단순하며 사용하기 쉽고, 가장 많이 사용하는 방식
  - <bean> 태그를 통해 세밀한 제어 가능

- **Annotation**

  - 어플리케이션의 규모가 커지고 빈의 개수가 많아질 경우 XML파일을 관리하는 것이 번거로움

  - **<u>빈으로 사용될 클래스에 annotation을 부여해주면 자동으로 빈 등록 가능</u>**

  - annotation으로 빈을 설정할 경우 반드시 component-scan을 설정해야 함

    - @Component : 객체를 생성할 대상 클래스에 작성해주는 것
    - 생성되는 bean의 이름은 클래스의 첫 글자를 소문자로 바꾼 것

    ![캡처](https://user-images.githubusercontent.com/97647987/176126288-006b637a-dfdd-4ad1-a5bf-b42b91a30ba8.JPG)

    - **@Autowired** : 의존성을 주입할 대상에 작성
      - Spring 컨테이너 내에서 타입 매칭 시행 : 컨테이너에 해당하는 타입의 bean이 있다면 매칭
      
      - 사용 가능 위치 : 생성자, Setter, field
        - @Qualifier를 이용하여 같은 타입이 여러개일 경우, bean을 지정하여 식별 가능
        
        

> #### 빈 생성 범위

![캡처](https://user-images.githubusercontent.com/97647987/176125804-e5117d2c-b878-4897-8d45-686ddd29657a.JPG)



>  #### Spring 프로젝트 라이브러리 세팅 with Maven

- 필요한 jar 파일 세팅 : [mvn repository](https://mvnrepository.com/)로 이동

- Maven : 빌드 관리 도구

  - 필요한 라이브러리 세팅

  - <u>Java 프로젝트 우클릭 - Configure - Convert to Maven Project</u>

    - Group Id : Maven 세상에서 라이브러리의 식별자, 주로 회사명
    - Artifact Id : Maven 세상에서 라이브러리의 식별자, 주로 프로젝트 명

  - <u>pom.xml 생성 : maven에 대한 전반적인 설정 파일</u>

    - java version 1.8로 컴파일

      ![캡처](https://user-images.githubusercontent.com/97647987/176596128-c619aeee-ecb9-4ef3-981b-f275610c36cb.JPG)

    - `build`태그 아래에`dependencies` 생성 후, Spring Context 입력

      ```xml
      <dependencies>
      	<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
      	<dependency>
      	    <groupId>org.springframework</groupId>
      	    <artifactId>spring-context</artifactId>
      	    <version>5.3.17</version>
      	</dependency>  
        </dependencies>
      ```



- 자동화가 되면서 가끔 라이브러리/환경이 꼬이는 상황 발생
  - C:\Users\Username\ .m2\repository : 다운받은 jar 파일이 모두 여기에 있음
  - 방법1 : 상단 메뉴 -> Project -> Clean
  - 방법2 : 우클릭 -> Maven -> Update Project(Alt + F5)
  - 방법3 : 이클립스 종료 -> .m2파일을 삭제 -> 이클립스를 실행



> #### Spring 설정 파일 생성

- spring container : 객체 관리 컨테이너
- 스프링 설정 파일 작성 후, ApplicationContext 빌드
  - <u>우클릭 -> New -> Source Folder(이름 : resources) : 스프링 설정 파일을 만들 폴더</u>
  - <u>resources 우클릭 -> New -> Spring Bean Configuration File -> 이름 : applicationContext(xml에 문서에서 규칙을 부여하기 위한 파일/스키마 정의 파일)</u>



> #### Spring Container에 빈(bean) 객체를 등록

- 컨테이너에 담아서 관리하는 객체 : bean

- applicationContext.xml

  ```xml
  <!-- 객체 등록 -->
  <!-- 식별을 위한 id 부여 -->
  <bean class="com.hj.di.Worker" id="worker"></bean>
  <bean class="com.hj.di.Desktop" id="computer"></bean>
  
  ```

- 컨테이너가 관리할 객체 등록을 하였으니 ComputerFactory.java 삭제



> #### Spring Container에 객체 빌드하기(applicationContext 빌드)

- CEO.java

  ```java
  package com.hj.di;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.GenericXmlApplicationContext;
  
  // Spring Container : 객체 관리 컨테이너
  // 스프링 설정 파일 작성 후, ApplicationContext 빌드
  public class CEO{
  	public static void main(String[] args) {
  		System.out.println("프로그램 시작");
  		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
  		System.out.println("컨테이너 만들어짐");
  		// 컨터이너로부터 내가 사용할 객체를 받아옴
  		// getBean의 반환유형 : Object -> 형변환 필요
  		
  		// Worker로 형변환
  		Worker worker = (Worker) context.getBean("worker");
  		// Desktop.class로 형변환
  		Computer computer = context.getBean("computer", Desktop.class);
  		worker.setComputer(computer);
  		worker.work();
  		
  		// 필요에 따라 여러번 받아오기 가능
  		Computer computer2 = context.getBean("computer", Desktop.class);
  		Computer computer3 = context.getBean("computer", Desktop.class);
  		
  		// object에 의해서 자기 자신의 주소 출력
  		// 모두 동일한 주소 출력
  		// Spring Container는 객체를 하나만 만들어, 갖고 있던 것을 계속 줌
  		
  		// 객체가 만들어지는 시점
  		// 1. 컨터이너가 만들어질 때/빌드할 때, 필요한 Bean 객체를 생성함
  		// 2. lazy-init속성을 true로 주면, 최초로 getBean이 호출되어 질때, 해당 객체를 생성
  		// 3. scope가 prototype이면 getBean을 할때마다 만들어냄
  		System.out.println(computer);
  		System.out.println(computer2);
  		System.out.println(computer3);
  	}
  }
  ```

