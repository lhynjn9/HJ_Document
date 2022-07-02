# spring DI

- 예시 코드 : SpringDI6_1, SpringDI6_2



> #### Spring Setting

- <u>Java 프로젝트 우클릭 - Configure - Convert to Maven Project</u>

- <u>pom.xml 생성 : maven에 대한 전반적인 설정 파일</u>

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

- <u>우클릭 -> New -> Source Folder(이름 : resources) : 스프링 설정 파일을 만들 폴더</u>

- <u>resources 우클릭 -> New -> Spring Bean Configuration File -> 이름 : applicationContext(xml에 문서에서 규칙을 부여하기 위한 파일/스키마 정의 파일)</u>

- <u>객체 등록 : applicationContext.xml</u>




> #### Annotation

- 빈으로 등록할 클래스에 `@Component`작성

- <u>applicationContext.xml에 `@Component`가 있는 클래스는 모두 빈으로 등록하라는 명령 작성</u>

  - <u>하단탭에서 Namespaces에서 추가적인 태그 선택 가능 : context 추가(다른 공간에 있는 것을 활용하기 위한 설정)</u>

- `<context:component-scan>`를 이용하여 등록

  ```xml
  <!-- @Component가 있는 클래스를 다 빈으로 등록 -->
  <context:component-scan base-package="com.hj.di"></context:component-scan>
  ```

- 설정자 주입은 추가로 `@Autowired`라는 지시자 필요
  - `@Autowired`로  적절한 것이 있으면 매개변수로 가져오라고 인식시켜주어야 함
- Qualifier가 없으려면 타입에 맞는 것이 하나만 존재하던가 타입이 여러개이면 Component에 이름을 지정
  - 타입 확인 -> 이름 확인