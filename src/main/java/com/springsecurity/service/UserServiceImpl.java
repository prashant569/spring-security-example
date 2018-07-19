package com.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springsecurity.model.UserProfile;
import com.springsecurity.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	UserRepository userDao;

	@Override
	public UserProfile findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	@Override
	public void add(UserProfile userProfile) {
		userDao.add(userProfile);
	}
	
}
