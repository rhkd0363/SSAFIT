package com.ssafit.pjt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafit.pjt.interceptor.JwtInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META_INF/resources/webjars/springfox-swagger");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","PUT","DELETE");
	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
		.excludePathPatterns("/api/user/","/api/user/login","/api/user/idcheck",
				"/swagger-resources/**",
				"/swagger-ui/**",
				"/v2/api-docs"
				);
	}
	
}
