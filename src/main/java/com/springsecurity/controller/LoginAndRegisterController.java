package com.springsecurity.controller;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springsecurity.model.UserProfile;
import com.springsecurity.service.UserService;



@Controller
public class LoginAndRegisterController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public ModelAndView loginForm(@RequestParam Optional<String> error, Principal principal) {

		ModelAndView mv = new ModelAndView();	
		
		if(principal==null) {
			
			mv.addObject("userProfile",new UserProfile());
			mv.addObject("loginFormClassValue","active");
			mv.setViewName("LoginAndRegister/LoginAndRegister");
			if(error.isPresent()) {
				mv.addObject("errorMessage", "Bad Credentials");
			}
		}
		else {
			
			mv.setViewName("redirect:/home");
		}
		
		
		return mv;
	}
	
	
	@RequestMapping("/home")
	public ModelAndView homePage(@RequestParam Optional<String> error,Principal principal) throws Exception {

		if(principal==null)
			throw new Exception("Principal cannot be null at home page : User is not logged in.");
		
		UserProfile userProfile = (UserProfile)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView mv = new ModelAndView();	
		mv.addObject("name", userProfile.getFirstName() + " " + userProfile.getLastName());
		mv.setViewName("home");		
		return mv;
	}
	
	
	@RequestMapping("/register")
	public ModelAndView showRegisterForm(Principal principal) {
		
		ModelAndView mv = new ModelAndView();
		
		if(principal==null) {
			mv.addObject("userProfile",new UserProfile());
			mv.addObject("registerFormClassValue","active");
			mv.setViewName("LoginAndRegister/LoginAndRegister");
		}
		else {
			
			mv.setViewName("redirect:/home");
		}
		
		
	

		return mv;
	}
	
	
	
	@PostMapping("/registerUser")
	public ModelAndView registerUser(@ModelAttribute UserProfile userProfile,Principal principal) {
		
		// sha 256 password encryption using Apache Commons Codec » 1.9
		String passwordSha256Hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(userProfile.getPassword()); 
		userService.add(new UserProfile(userProfile.getFirstName(),userProfile.getLastName(),userProfile.getUsername(), passwordSha256Hex));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");

		return mv;
	}
	
	@RequestMapping("/checkForExistingUsername")
	@ResponseBody
	public String checkForExistingUsername(@RequestParam("username") String username) {
		
		UserProfile user = null;		
		String errorMessage = null;
		
		try {
			user = userService.findByUsername(username);
		}
		catch (Exception ex) {
			System.out.println("Exception :" + ex);
			return "Something unexpected happen. Please try again";
		}
		
		finally {
			if(user != null) {
				errorMessage = "Username already exist";
			}
			return errorMessage;
		}	
	}
	
	
	
}
