package com.springsecurity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserProfile  {

	@Id
	private String username;
	
	private String firstName;
	private String lastName;
	private String password;
	private String role;

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserProfile(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = "user";
	}

	public UserProfile(String username, String password) {

		this.username = username;
		this.password = password;
	}

	public UserProfile(UserProfile userProfile) {
		this.firstName = userProfile.getFirstName();;
		this.lastName = userProfile.getLastName();
		this.username = userProfile.getUsername();
		this.password = userProfile.getPassword();
		this.role = userProfile.getRole();
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
