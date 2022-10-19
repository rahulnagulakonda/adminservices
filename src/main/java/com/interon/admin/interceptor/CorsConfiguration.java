package com.interon.admin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedHeaders("*","Access-Control-Allow-Origin","Access-Control-Allow-Methods","Access-Control-Allow-Headers")
		.allowedMethods("*")
		.allowedOrigins("http://localhost:4200")
		.exposedHeaders("*","Access-Control-Allow-Origin","Access-Control-Allow-Methods","Access-Control-Allow-Headers");
	}
	
}
