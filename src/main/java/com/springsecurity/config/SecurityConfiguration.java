package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("*.js")
                .antMatchers("*.css")
                .antMatchers("/imaages/**")
                .antMatchers("*.ico")
                .antMatchers("*.woff2")
                .antMatchers("/resources/**");
    }
	
	@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		 authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder()).and().eraseCredentials(false);
    }
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable();
		
		/*httpSecurity
					.authorizeRequests()
					.antMatchers("**//*secured*//**").authenticated()
					.anyRequest().permitAll()
					.and().formLogin().loginPage("/").usernameParameter("username")
					.permitAll();	*/	
					
					
		httpSecurity
					.authorizeRequests()
					.antMatchers("/register").permitAll()
					.antMatchers("/registerUser").permitAll()
					.antMatchers("/checkForExistingUsername").permitAll()
					.antMatchers("/error").permitAll()
					.anyRequest().authenticated()
					.and().formLogin()
					.loginPage("/").failureUrl("/?error").defaultSuccessUrl("/home")
					.usernameParameter("username")
					.permitAll()
					.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll();
		


		
	}
	
	
	
	
	private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
            	String passwordSha256Hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(charSequence.toString());            	
            	return passwordSha256Hex.equals(s);
                		 
            }
        };
	}
}
