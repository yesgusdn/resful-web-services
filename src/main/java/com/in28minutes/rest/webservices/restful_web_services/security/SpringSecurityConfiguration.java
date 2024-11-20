package com.in28minutes.rest.webservices.restful_web_services.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		// 1) All request should be authenticated
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

		// 2) If a request is not authenticated, a web page is down
		http.httpBasic(withDefaults());
		
		// 3) CSRF -> POST, PUT
		http.csrf(csrf -> csrf.ignoringRequestMatchers("*"));
		
		return http.build();
	}
}
