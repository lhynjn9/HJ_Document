# Spring

> #### Spring Framework

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
    
  - <u>Ioc/DI(Inversion of Control : 제어의 반전/  Dependency Injection : 의존성 지원)</u>
    
    - DI는 유연하게 확장 가능한 객체를 만들어 두고 객체 간의 의존 관계는 외부에서 다이나믹하게 설정
    
  - AOP(Aspect Oriented Programming : 관점 지향 프로그래밍)
    - 관심사의 분리를 통해 소프트웨어 모듈성을 향상
    - 공통 모듈을 여러 코드에 쉽게 적용 가능
    
    

> #### Spring Framework의 특징

- 경량 컨테이너
- DI(Dependency Injection - 의존성 지원) 패턴 지원
  - 스프링은 설정 파일이나, 어노테이션을 통해서 객체 간의 의존 관계를 설정할 수 있음
  - 따라서, 객체는 의존하고 있는 객체를 직접 생성하거나 검색할 필요가 없음
- AOP(Aspect Oriented Programming : 관점 지향 프로그래밍) 지원
- POJO(Plain Old Java Object) 지원
  - 특정 인터페이스를 구현하거나 또는 클래스를 상속하지 않는 일반 자바 객체 지원
- Ioc(Inversion of Control : 제어의 반전)
  - 스프링의 핵심 기능
  - 자바의 객체 생성 및 의존관계에 있어 모든 제어권은 개발자에게 있음
- 트랜잭션 처리를 위한 일관된 방법을 제공
- 영속성과 관련도니 다양한 API를 지원
- 다양한 API에 대한 연동을 지원
