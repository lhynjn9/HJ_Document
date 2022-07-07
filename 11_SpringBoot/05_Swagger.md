# 0Swagger

- 기존 문서롤 사용하던 문제를 해결하기 위해 Swagger를 사용

- 간단한 설정으로 프로젝트의 API목록을 웹에서 확인 및 테스트 할 수 있게 해주는 Library

- Swagger를 사용하면 Controller에 정의되어 있는 모든 URL을 바로 확인할 수 있음

- API 목록 뿐 아니라 API의 명세 및 설명도 볼 수 있으며, 또한 API를 직접 테스트해 볼 수도 있음

- 실습

  - springfox boot starter 추가

    ```xml
    <dependency>
    		    <groupId>io.springfox</groupId>
    		    <artifactId>springfox-boot-starter</artifactId>
    		    <version>3.0.0</version>
    		</dependency>
    ```

  - Swagger-3.0버전이 boot 2.6이상 버전과 충돌로 에러가 발생한 경우

    - src/main/java/com/hj/swagger/configuration/WebConfig.java 생성
    
    ```
    package com.hj.swagger.configuration;
    
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.EnableWebMvc;
    import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
    
    @Configuration // 환경설정 역할
    @EnableWebMvc //MVC를 덮어 쓸 수 있게
    public class WebConfig implements WebMvcConfigurer{
    
    	@Override
    	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    		registry.addResourceHandler("/swagger-ui/**")
    				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    			
    	}
    	
    }
    ```
    
  
  
  
  - http://localhost:8080/swagger-ui/index.html 로 정상 접속 확인
  
  - src/main/java/com/hj/swagger/model/dto/Board.java 생성
  
    ```java
    package com.hj.swagger.model.dto;
    
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    
    
    @ApiModel(value="BoardDTO", description="게시글 정보를 표현한다")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Board {
    	
    	@ApiModelProperty(value="게시글 고유 번호", example="10")
    	private int id;
    	private String writer;
    	private String title;
    	private String content;
    }
    
    ```
  
  - src/main/java/com/hj/swagger/controller/BoardController.java 생성
  
    ```java
    package com.hj.swagger.controller;
    
    import java.util.Arrays;
    import java.util.List;
    
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    import com.hj.swagger.model.dto.Board;
    
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiImplicitParam;
    import io.swagger.annotations.ApiImplicitParams;
    import io.swagger.annotations.ApiOperation;
    import io.swagger.annotations.ApiParam;
    
    //@ApiIgnore // api 목록에서 제외
    @Api(tags="게시글") // 이름 명명
    @RestController
    @RequestMapping("/api")
    public class BoardController {
    	
    	@ApiOperation(
    			value = "게시글 조회",
    			notes = "페이지에 해당하는 게시글 목록을 반환"
    			) // value = url의 설명, notes = 부가적인 설명 작성 가능
    	// 매개변수에 대한 설명
    	@ApiImplicitParams({
    		@ApiImplicitParam(name="pageNo", value="페이지 번호", required=true ),
    		@ApiImplicitParam(name="key", value="제목|내용|제목+내용"),
    		@ApiImplicitParam(name="word", value="검색어")
    	})
    	@GetMapping("/board")
    	public ResponseEntity<List<Board>> retrieveBoard(
    			int pageNo, 
    			String key, 
    			String word){
    		return new ResponseEntity<List<Board>>(
    				Arrays.asList(
    						new Board(1, "ssafy", "swagger", "swagger 연숩"),
    						new Board(2, "ssafy2", "swagger2", "swagger 연숩2")
    				),
    				HttpStatus.OK
    			);
    	}
    	
    }
    
    ```
  
  - src/main/java/com/hj/swagger/configuration/SwaggerConfig.java 수정
  
    ```java
    package com.hj.swagger.configuration;
    
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    
    import springfox.documentation.builders.ApiInfoBuilder;
    import springfox.documentation.builders.PathSelectors;
    import springfox.documentation.builders.RequestHandlerSelectors;
    import springfox.documentation.service.ApiInfo;
    import springfox.documentation.spi.DocumentationType;
    import springfox.documentation.spring.web.plugins.Docket;
    
    // 내가 원하는 것만 보이게 설정하기 위한 부분
    
    // swagger : 설정정보가 있는 빈을 찾음
    @Configuration 
    public class SwaggerConfig {
    	
    	@Bean
    	public Docket api() {
    		return new Docket(DocumentationType.OAS_30)
    				// 반환 타입 : ApiSelectorBuilder
    				.select()
    				// 반환 타입 : ApiSelectorBuilder, 아래 설정한 패키지 아래 있는 것만 api로 등록 하겠다는 의미
    				.apis(RequestHandlerSelectors.basePackage("com.hj.swagger.controller"))
    				// 반환 타입 : ApiSelectorBuilder
    				.paths(PathSelectors.ant("/api/**"))
    				// 반환 타입 : Docker
    				.build()
    				.apiInfo(apiInfo());
    	}
    	
    	private ApiInfo apiInfo() {
    		return new ApiInfoBuilder()
    				// ApiInfoBuilder
    				.title("hj Swagger")
    				.description("HJ Backend Swagger API 테스트")
    				.version("v1")
    				.build();
    	}
    }
    
    ```
  
  - http://localhost:8080/swagger-ui/index.html 로 controller 정보의 정상 출력 확인