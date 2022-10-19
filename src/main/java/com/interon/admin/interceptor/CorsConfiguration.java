package com.interon.admin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedHeaders("*","Access-Control-Allow-Origin","Access-Control-Allow-Methods","Access-Control-Allow-Headers")
		.allowedMethods("*")
		.allowedOrigins("*")
		.exposedHeaders("*","Access-Control-Allow-Origin","Access-Control-Allow-Methods","Access-Control-Allow-Headers");
	}
	
}
