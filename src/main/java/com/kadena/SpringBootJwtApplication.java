package com.kadena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJwtApplication {
//
//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//    
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(new JwtFilter());
//		registrationBean.addUrlPatterns("/secure/*");
//		return registrationBean;
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}
}
