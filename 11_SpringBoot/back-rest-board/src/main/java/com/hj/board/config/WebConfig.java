// 3.

package com.hj.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 환경설정 역할
@EnableWebMvc //MVC를 덮어 쓸 수 있게 해주는 역할
public class WebConfig implements WebMvcConfigurer{
	
	// 3. Swagger 설정 : Swagger-3.0 버전이 boot 2.6이상 버전과 충돌로 에러가 발생한 경우
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
			
	}

}
