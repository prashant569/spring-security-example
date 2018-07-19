package com.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringSecurityExampleApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExampleApplication.class, args);
	}
	
	
	   @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(SpringSecurityExampleApplication.class);
	    }
}
