// 4. Swagger 전용 설정 파일

package com.hj.board.config;

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
				.apis(RequestHandlerSelectors.basePackage("com.hj.board.controller"))
				// 반환 타입 : ApiSelectorBuilder
				.paths(PathSelectors.ant("/api/**"))
				// 반환 타입 : Docker
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// ApiInfoBuilder
				.title("hj Board Swagger")
				.description("HJ 게시판 REST API 테스트")
				.version("1.0")
				.build();
	}
}
