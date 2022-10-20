/*
 * package com.interon.admin.security;
 * 
 * import java.util.Arrays;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.web.cors.CorsConfiguration; import
 * org.springframework.web.cors.CorsConfigurationSource; import
 * org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 * 
 * @EnableWebSecurity public class WebSecurityConfiguration{
 * 
 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
 * Exception { http.cors(); return http.build(); }
 * 
 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
 * configuration = new CorsConfiguration();
 * configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
 * configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE",
 * "OPTIONS","PATCH","HEAD"));
 * configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin",
 * "*"));
 * configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin",
 * "*")); UrlBasedCorsConfigurationSource source = new
 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
 * configuration); return source; } }
 */