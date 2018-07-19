package com.springsecurity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springsecurity.model.UserProfile;


public interface UserService {

	public UserProfile findByUsername(String username);
	public void add(UserProfile userProfile);
	
}
