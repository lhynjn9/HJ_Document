# REST API

- REST관련 Annotation

  - ResponseEntity : 데이터 응답 시 [상태코드, 헤더, 데이터] 설정이 가능

  ![캡처](https://user-images.githubusercontent.com/97647987/177384016-537f4eae-cd41-4d0c-a2f5-226553d25271.JPG)

- ResponseBody

  - 반환 타입
    - 맵, DTO -> json 형태
    - 리스트 -> json 배열 형태

- Dependencies 추가

  - Spring Boot DevTools
  - Spring Web
  - Lombok



1. src/main/java/com/hj/rest/controller/TestController1.java 생성

   ```java
   package com.hj.rest.controller;
   
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.List;
   import java.util.Map;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.ResponseBody;
   
   @Controller
   @RequestMapping("/rest1")
   public class TestController1 {
   	// http://localhost:8080/rest1/test1 : 페이지를 찾지 못함
   	@GetMapping("/test1")
   	public String test1() {
   		return "hi rest";		
   	}
   	
   	// http://localhost:8080/rest1/test2
   	@GetMapping("/test2")
   	@ResponseBody // 일반 데이터로 인식
   	public String test2() {
   		return "hi rest";		
   	}
   	
   	// http://localhost:8080/rest1/test2
   	// 객체를 보내면 json 형태로 전송
   	// 스프링이 변환시켜서 전송
   		@GetMapping("/test3")
   		@ResponseBody // 일반 데이터로 인식
   		public Map<String, String> test3() {
   			Map<String, String> data = new HashMap<>();
   			data.put("id", "sbs");
   			data.put("name", "이현정");
   			data.put("email", "naver");
   			return data;	
   		}
   		
   		
   		// dto 객체
   		@GetMapping("/test4")
   		@ResponseBody // 일반 데이터로 인식
   		public Member test4() {
   			Member m = new Member();
   			
   			m.setId("dl");
   			m.setPassword("dfd");
   			m.setEmail("naver");
   			
   			return m;
   		}
   		
   		@GetMapping("/test5")
   		@ResponseBody 
   		// json 배열 형태로 출력 : [{}, {}, {}, ...]
   		public List<Member> test5() {
   			List<Member> list = new ArrayList<>();
   			for(int i = 1; i < 4; i++) {
   				Member m = new Member();
   				m.setId("sbs" + i);
   				m.setPassword("이현정" + i);
   				m.setEmail("email" + i);
   				list.add(m);
   			}
   			return list;
   			}
   }
   
   ```
   
   

2. src/main/java/com/hj/rest/controller/Member.java 생성

   ```java
   package com.hj.rest.controller;                       
                                                         
   public class Member {                                 
   	private String id;                                
   	private String password;                          
   	private String email;                             
   	                                                  
   	public String getId() {                           
   		return id;                                    
   	}                                                 
   	public void setId(String id) {                    
   		this.id = id;                                 
   	}                                                 
   	public String getPassword() {                     
   		return password;                              
   	}                                                 
   	public void setPassword(String password) {        
   		this.password = password;                     
   	}                                                 
   	public String getEmail() {                        
   		return email;                                 
   	}                                                 
   	public void setEmail(String email) {              
   		this.email = email;                           
   	}                                                 
                                                         
   	                                                  
   }                                                     
                                                         
   ```



3. src/main/java/com/hj/rest/controller/TestController2.java 생성

   ```java
   package com.hj.rest.controller;
   
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.List;
   import java.util.Map;
   
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.ResponseBody;
   import org.springframework.web.bind.annotation.RestController;
   
   
   // 하나의 클래스에 뷰와 rest를 제공하고 싶다면
   // Controller + ResponseBody
   @RestController
   @RequestMapping("/rest2")
   public class TestController2 {
   	// http://localhost:8080/rest1/test1 : 페이지를 찾지 못함
   	@GetMapping("/test1")
   	public String test1() {
   		return "hi rest";		
   	}
   	
   	// http://localhost:8080/rest1/test2
   	@GetMapping("/test2")
   	public String test2() {
   		return "hi rest";		
   	}
   	
   	// http://localhost:8080/rest1/test2
   	// 객체를 보내면 json 형태로 전송
   	// 스프링이 변환시켜서 전송
   		@GetMapping("/test3")
   		public Map<String, String> test3() {
   			Map<String, String> data = new HashMap<>();
   			data.put("id", "sbs");
   			data.put("name", "이현정");
   			data.put("email", "naver");
   			return data;	
   		}
   		
   		
   		// dto 객체
   		@GetMapping("/test4")
   		public Member test4() {
   			Member m = new Member();
   			
   			m.setId("dl");
   			m.setPassword("dfd");
   			m.setEmail("naver");
   			
   			return m;
   		}
   		
   		@GetMapping("/test5")
   		public List<Member> test5() {
   			List<Member> list = new ArrayList<>();
   			for(int i = 1; i < 4; i++) {
   				Member m = new Member();
   				m.setId("sbs" + i);
   				m.setPassword("이현정" + i);
   				m.setEmail("email" + i);
   				list.add(m);
   			}
   			return list;
   			}
   }
   
   ```

4. src/main/java/com/hj/rest/controller/TestController3.java 생성

   ```java
   package com.hj.rest.controller;
   
   import org.springframework.web.bind.annotation.DeleteMapping;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.PutMapping;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   @RequestMapping("/rest3") // 공통 url
   public class TestController3 {
   	@GetMapping("/test1")
   	public String test1() {
   		return "GET";
   	}
   	
   	@PostMapping("/test2")
   	public String test2() {
   		return "POST";
   	}
   	
   	@PutMapping("/test3")
   	public String test3() {
   		return "PUT";
   	}
   	
   	@DeleteMapping("/test4")
   	public String test4() {
   		return "DELETE";
   	}
   	
   
   }
   
   ```

5. src/main/java/com/hj/rest/controller/TestController4.java 생성

   ```java
   package com.hj.rest.controller;
   
   import org.springframework.http.HttpHeaders;
   import org.springframework.http.HttpStatus;
   import org.springframework.http.ResponseEntity;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.RequestBody;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   @RequestMapping("/rest4")
   public class TestController4 {
   
   	@GetMapping("/board/{id}")
   	// 파라미터가 아닌 path의 일부로 들어온 id를 받아야 함
   	// 변수 이름이 다르다면 직접 매핑해줘야 함
   	public String test1(@PathVariable("id") int no) {
   	// public String test1(@PathVariable int id) {
   		
   		return "PathVariable" + no;
   	}
   	
   	
   	// 클라이언트가 보내준 것이 json형태일 때 알려주는 것 @RequsetBody
   	@PostMapping("/test2")
   	public String test2(@RequestBody Member m) {
   		return String.format("id: %s, email: %s", m.getId(), m.getEmail());
   		
   	}
   	
   	// ResponseEntity : 응답하려는 데이터(Body) + 응답 헤더(헤더) + 응답코드(시작 라인) 조작 가능
       // 응답 헤더는 옵션
   	// 자체로 ResponseBody를 포함
   	// ResponseEntity<T> : T <- Body에 들어갈 데이터의 타입
   	@GetMapping("/test3")
   	public ResponseEntity<String> test3(){
   		HttpHeaders headers = new HttpHeaders();
   		headers.add("auth", "12341234");
   		return new ResponseEntity<String>("OK 성공", headers, HttpStatus.OK);
   		
   	}
   	 
   }
   ```
   
   
   
   - Lombok 설치
     - [여기](https://projectlombok.org/)에 접속
     - 롬복을 다운 받은 위치에서 cmd 열기
     - `java -jar lombok.jar` 입력
     - specify location 클릭해서 sts 찾기
     - install
     - sts 재실행
     - sts 폴더에  lombok 설치 확인
     - STS.ini 파일의 -javaagent 경로에 한글이 있다면 .\lombok.jar로 상대 경로로 변경
   
   
   
   - 실습
   
     - src/main/java/com/hj/rest/controller/Member.java 수정
   
       ```java
       package com.hj.rest.controller;
       
       import lombok.AllArgsConstructor;
       import lombok.Data;
       import lombok.NoArgsConstructor;
       
       @Data // setter, getter 한번에 생성
       @NoArgsConstructor // 매개변수가 없는 생성자 생성
       @AllArgsConstructor // 모든 멤버 변수로 생성자를 생성
       public class Member {
       	private String id;
       	private String password;
       	private String email;
       
       }   
       
       ```
   
       