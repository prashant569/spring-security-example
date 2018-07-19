package com.springsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.model.CustomUserDetails;
import com.springsecurity.model.UserProfile;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserProfile userProfile = userService.findByUsername(username);		
		Optional<UserProfile> optionalUser =  Optional.ofNullable(userProfile);
		
		if(userProfile==null) {
			//optionalUser.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
			  optionalUser.orElseThrow(()-> new UsernameNotFoundException("UsernameNotFoundException"));
		}			
		
		return optionalUser.map(CustomUserDetails::new).get();
	}
	

}
