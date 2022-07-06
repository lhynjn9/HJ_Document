package com.hj.board.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // 현재 설정하는 것으로 mvc를 사용하겠다는 의미
public class WebConfig implements WebMvcConfigurer{
	// 환경 설정 파일에 있는 변수 값을 이용
	@Value("${spring.mvc.view.prefix}")
	private String prefix;
	@Value("${spring.mvc.view.suffix}")
	private String suffix;
	
	
	// 직접 viewresolver 등록
	@Bean
	public ViewResolver internalViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(prefix);
		resolver.setSuffix(suffix);
		return resolver;
	}
	// 직접 viewresolver 등록
	@Bean
	public ViewResolver beanViewResolver() {
		BeanNameViewResolver resolver = new  BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
		
	}
}
