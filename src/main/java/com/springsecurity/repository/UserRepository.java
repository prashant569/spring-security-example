package com.springsecurity.repository;

import com.springsecurity.model.UserProfile;


public interface UserRepository /*extends MongoRepository<UserProfile, String>*/ {
	
	public UserProfile findByUsername(String username);	
	public void add(UserProfile user);
}
