package com.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Component
public class MyBean {

	@Bean
    public MongoDbFactory mongoDbFactory() {
    	return new SimpleMongoDbFactory(new MongoClient("localhost",27017), "SecurityTest");
    }
    
    @Bean
    public MongoTemplate mongoTemplate() {
    	return new MongoTemplate(mongoDbFactory());
    }

   
   
}