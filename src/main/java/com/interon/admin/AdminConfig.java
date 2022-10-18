package com.interon.admin;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.interon.admin.util.AwsUtils;

@Configuration
@Profile("!local")
public class AdminConfig {

	@Autowired
	private AwsUtils awsUtils;
	
	@Value("${environment}")
	private String env;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		
		return DataSourceBuilder.create()
				.url(awsUtils.getParaValue("/"+env+"/db-url"))
				.username(awsUtils.getParaValue("/"+env+"/db-username"))
				.password(awsUtils.getParaValue("/"+env+"/db-password"))
				.build();

	}
}
