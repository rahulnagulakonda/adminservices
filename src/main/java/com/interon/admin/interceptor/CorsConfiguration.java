
package com.interon.admin.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	@Bean
	public WebMvcConfigurer getCorsConfiguration() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*","Authorization","Content-Type")
						.maxAge(3600)
						.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Methods","Access-Control-Allow-Headers","Access-Control-Max-Age");
			}
		};
	}

}
