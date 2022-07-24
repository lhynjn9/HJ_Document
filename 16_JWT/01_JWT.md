# JWT(JSON Web Token)

- 서버 인증 방식의 문제점

  - 세션 : 로그인 유저의 수가 많아지면 서버에 저장해야 할 정보가 많아짐
  - 확장성 : 여러 개의 프로세스를 돌리거나, 여러 대의 서버 컴퓨터를 추가하는 과정이 복잡하고 어려움
  - CORS(Cross-Origin Resource Sharing)

  👉토큰 인증 방식

- 토큰 인증 방식

  ![image-20220723232257642](https://user-images.githubusercontent.com/97647987/180609275-93352ae8-4512-489d-9998-1bea32ec0af5.png)

  

> #### JWT

- JSON Web Token
- 토큰 인증 방식 중 하나
- 정보를 JSON 형태로 안전하게 전송하기 위한 웹 표준(RFC7519)
- 디지털 서명이 되어 있으므로 신뢰할 수 있음
- 암호화 알고리즘을 사용
- JWT 자체가 필요한 모든 정보를 갖고 있기 때문에 검증하기 위해 다른 검증 수단이 필요하지 않음
- 권한을 부여하거나, 정보를 교환할 때 등의 상황에서 사용 가능

- 구조 : `.`(dot)을 이용하여 세부분으로 구성됨

  ```
  header.payload.signature
  ```

  1. header(헤더)

     - 일반적으로 토큰의 유형과 사용 중인 암호 알고리즘으로 구성

       ```json
       {
           "alg": "HS256",
           "typ": "JWT"
       }
       ```

  2. payload(내용)

     - claim을 포함한 payload 작성

     - claim은 사용자 및 추가 데이터를 의미, 등록/공개/비공개가 있음

       ```json
       {
           "sub": "123456798",
           "name": "Sue",
           "iat": 15162355
       }
       ```

  3. signature(서명)

     - 해당 토큰이 조작되었거나 변경되지 않음을 확인하는 용도로 사용

     - Header와 Payload의 인코딩 값을 더하고, 비밀 키로 암호화 알고리즘을 통해 해쉬 값을 생성

       ```json
       HMACSHA256(
       	base64UrlEncoding(header) + "." + base64UrlEncoding(payload), secret
       )
       ```

       


> #### JWT 등록

- pom.xml에 JSON Web Token Support For The JWM 등록
