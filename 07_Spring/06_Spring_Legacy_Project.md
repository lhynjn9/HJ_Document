# Spring Legacy Project

- Spring_Legacy

- 기본 설정이 어느정도 되어 있는 Project

- New -> Spring Legacy Projcet -> Spring MVC Project -> 최소 3개의 깊이의 패키지 설정 -> Finish

- pom.xml

  - java, spring-context 등 여러 라이브러리가 들어 있음

  - 버전이 너무 구번전 : 아래와 같이 수정

    ```xml
    <!--수정1-->
    <properties>
    		<java-version>1.8</java-version>
    		<org.springframework-version>5.3.17</org.springframework-version>
    		<org.aspectj-version>1.9.8</org.aspectj-version>
    		<org.slf4j-version>1.7.36</org.slf4j-version>
    	</properties>
    <!--추가 1-->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>${org.aspectj-version}</version>
    </dependency>
    <!--수정2-->
    <artifactId>maven-compiler-plugin</artifactId>
                    <version>2.5.1</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                        <compilerArgument>-Xlint:all</compilerArgument>
                        <showWarnings>true</showWarnings>
                        <showDeprecation>true</showDeprecation>
                    </configuration>
    ```

    

- main : 배포할 때, 실제 동작할 코드를 넣는 곳

  - webapp/WEB-INF/web.xml에 DispatcherServlet과 설정 파일이 작성되어 있음
  - filter는 없으므로 필요하므로 작성하면 됨

- test : 단계별 테스트용 테스트 코드

- servlet-context.xml

  ```xml
  <!--모든 요청 url을 DispatcherSerlvet에게 가므로, 왔을때 예외를 두는 공간-->
  <resources mapping="/resources/**" location="/resources/" /> 
  ```

- root-context.xml

  - 설정 파일을 등록하면 됨

- src/main/resources/ : 마이바티스 설정 파일 공간

- src/main/java/

- 실행 시, 경로

  - `http://localhost:8080/lagacy/` : root context 경로
  - 바꾸는 방법 : 우클릭 -> Properties -> Web Project Settings를 변경

