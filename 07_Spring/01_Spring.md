# Spring

> #### Spring Framework

- 객체 관리 컨테이너
- 엔터프라이즈 급 애플리케이션을 만들기 위한 모든 기능을 종합적으로 제공하는 경량화 된 솔루션



> #### Spring Framework의 구조

- Spring 삼각형 : Enterprise Application 개발 시 복잡함을 해결하는 Spring의 핵심
  - POJO(Planin Old Java Object)
    - <u>특정 환경이나 기술에 종속적이지 않은 객체지향 원리에 충실한 자바 객체</u>
    - 테스트하기 용이하며, 객체지향 설계를 자유롭게 적용할 수 있음
    
  - PSA(Portable Service Abstraction)
    - 환경과 세부기술의 변경과 관계 없이 일관된 방식으로 기술에 접근할 수 있게 해주는 설계 원칙
    - 트랜잭션 추상화, OXM 추상화, 데이터 액세스의 Exception 변환기능 등 기술적인 복잡함은 추상화를 통해 Low Level의 기술 구현 부분과 기술을 사용하는 인터페이스로 분리
    - 예를 들어, 데이터베이스에 관계없이 동일하게 적용할 수 있는 트랜잭션 처리 방식
    
  - **<u>Ioc/DI(Inversion of Control : 제어의 반전/  Dependency Injection : 의존성 지원)</u>**
    
    - **DI**
      - **객체간 의존성이 존재할 경우, 개발자는 의존성에 관련한 설정만 해주면 실제 의존성 생성은 프레임워크가 담당**
      - **유연하게 확장 가능한 객체를 만들어 두고 객체 간의 의존 관계는 외부에서 다이나믹하게 설정**
    - IoC
      - 제어의 역전을 통해 객체 및 프로세스의 제어를 프레임워크가 담당
    
  - AOP(Aspect Oriented Programming : 관점 지향 프로그래밍)
    - 관심사의 분리를 통해 소프트웨어 모듈성을 향상
    - 공통 모듈을 여러 코드에 쉽게 적용 가능
    
    

> #### Spring Framework의 특징

- 경량 컨테이너 : 스프링은 자바객체를 담고있는 컨테이너이며, 스프링 컨테이너는 이들 자바 객체의 생성과 소명과 같은 라이플사이클을 관리한다. 언제든지 스프링컨테이너로부터 필요한 객체를 가져와 사용할 수 있음
- DI(Dependency Injection - 의존성 지원) 패턴 지원
  - <u>스프링은 설정 파일이나, 어노테이션을 통해서 객체 간의 의존 관계를 설정할 수 있음</u>
  - 따라서, 객체는 의존하고 있는 객체를 직접 생성하거나 검색할 필요가 없음
- AOP(Aspect Oriented Programming : 관점 지향 프로그래밍) 지원
  - AOP는 문제를 바라보는 관점을 기준으로 프로그래밍하는 기법으로, 문제해결을 위한 핵심기능과 전체에 적용되는 공통기능을 기준으로 나누어 프로그래밍 하는 기법
  - 스프링은 자체적으로 프록시 기반의 AOP를 지원하므로 트랜잭션이나, 로깅, 보안과 같이 여러 모듈에서 공통으로 필요로 하지만, 실제 모듈의 핵심이 아닌 기능들을 분리하여 각 모듈에 적용이 가능
- POJO(Plain Old Java Object) 지원
  - 특정 인터페이스를 구현하거나 또는 클래스를 상속하지 않는 일반 자바 객체 지원
  - 스프링 컨테이너에 저장되는 자바객체는 특정한 인터페이스를 구현하거나 클래스 상속없이도 사용이 가능
  - 일반적인 자바객체를 칭하기 위한 별칭의 개념
- Ioc(Inversion of Control : 제어의 반전)
  - 스프링의 핵심 기능
  - 자바의 객체 생성 및 의존관계에 있어 모든 제어권은 개발자에게 있음
  - 스프링에서도 객체에 대한 생성과 생명주기를 관리할 수 있는 기능을 제공하고 있으므로 "Spring Container" ,"IoC Container"라고 부르기도 함
- 트랜잭션 처리를 위한 일관된 방법을 제공
- 영속성과 관련된 다양한 API를 지원
- 다양한 API에 대한 연동을 지원



> #### Spring Framework Module

 ![image](https://user-images.githubusercontent.com/97647987/175879320-99dd88c6-c29d-4f32-a46a-606f77eb6eb8.png)

| **Spring Core**    | 스프링 프레임워크의 핵심기능을 제공하며, Core컨테이너의 주요컴포넌트는 BeanFactory이다. |
| ------------------ | ------------------------------------------------------------ |
| **Spring Context** | 국제화된 메시지, Application 생명주기 이벤트, 유효성 검증 등을 지원함으로써, BeanFactory의 개념을 확장한다. |
| **Spring AOP**     | 설정 관리 기능을 통해 AOP기능을 스프링프레임워크과 직접 통합시킨다. |
| **Spring DAO**     | Spring JDBC DAO추상레이어는 다른 데이터베이스 벤더들의 예외 핸들링과 오류메시지를 관리하는 중요한 예외계층을 제공한다. |
| **Spring ORM**     | 스프링프레임워크는 여러 ORM프레임워크에 플러그인되어, Object Relarional 툴(JDO,IBatis)을 제공한다. |
| **Spring Web**     | Web Context module은 Application Context module 상위에 구현되어, Web기반 Application context를 제공한다. |
| **Spring Web MVC** | 스프링 프레임워크는 자체적으로 MVC워크를 제공하고 있으며, 스프링만 사용해도 MVC기반의 웹 어플리케이션을 어렵지 않게 개발이 가능하다. |

 

> #### IoC(Inversion of Control, 제어의 역행)

- 객체를 제어하고 관리하는 역할이 개발자에게서 스프링 컨테이너에게 반전된다는 의미

- **<u>객체 제어 방식 : 객체 생성을 Container에게 위임하여 처리</u>**

- 객체지향 언어에서 Object 간의 연결 관계를 런타임에 결정

- 객체 간의 관계가 느슨하게 연결됨 = 객체 간의 결합도를 떨어뜨릴 수 있음

- <u>IoC의 구현 방법 중 하나가 DI(어노테이션을 통해서 객체 간의 의존 관계를 설정)</u>

  - 객체가 다른 인터페이스에 의존한다고 선언하면 실행 시, 스프링 프레임워크가 해당 의존성의 인스턴스를 주입

    ![캡처](https://user-images.githubusercontent.com/97647987/175946343-9e47915b-a429-48f9-8628-faf2de594459.JPG)

  

> #### IoC 유형

- Dependency Lookup(의존성 검색)
  - 컨테이너가 lookup context를 통해서 필요한 Resource나 Object를 얻는 방식
  - JNDI 이외의 방법을 사용한다면 JNDI 관련 코드를 오브젝트 내에서 일일이 변경해주어야 함
  - Lookup한 Object를 필요한 타입으로 Casting 해주어야 함
  - Naming Exception을 처리하기 위한 로직이 필요
- Dependency Injecton(의존성 주입)
  - Object에 Lookup 코드를 사용하지 않고 <u>**컨테이너가 직접 의존 구조를 Object에 설정할 수 있도록 지정해주는 방식**</u>
  - Object가 컨테이너의 존재 여부를 알 필요가 없음
  - Lookup 관련된 코드들이 Object 내에서 사라짐
  - Setter Injection과 Constructor Injection, Method Injection이 있음


