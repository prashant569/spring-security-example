package com.springsecurity.resource;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {

	@GetMapping("/all")
	public String hello() {
		return "This resouce is exposed to any kind of user";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/secured/all")
	public String securedHello() {
		return "This is a secured resource. It is only accessible to admins.";
	}
	
}
