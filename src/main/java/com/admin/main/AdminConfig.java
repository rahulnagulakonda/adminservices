package com.admin.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.admin.main.util.AwsUtil;

@Configuration
public class AdminConfig {
	
	@Autowired
	private AwsUtil awsUtil;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.url("jdbc:postgresql://interon-db.cgm7zlub4cmv.us-east-1.rds.amazonaws.com:5432/interon")
				.username("interon")
				.password(awsUtil.getParaValue("/dev/db-password"))
				.build();
	}
}
