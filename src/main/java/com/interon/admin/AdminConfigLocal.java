package com.interon.admin;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class AdminConfigLocal {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSourceLocal() {

		return DataSourceBuilder.create()
				.url("jdbc:postgresql://localhost:5432/interon")
				.username("postgres")
				.password("September2022!")
				.build();
	}
}
