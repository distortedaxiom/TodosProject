package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService; 
	
	// configuration for the authentication (who are you?)
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		
//		auth.inMemoryAuthentication()
//			.withUser("user1")
//			.password( passwordEncoder().encode("123") )
//			.roles("USER")
//			.and()
//			.withUser("admin1")
//			.password( passwordEncoder().encode("123") )
//			.roles("ADMIN")
//			.and()
//			.withUser("dev1")
//			.password( passwordEncoder().encode("123") )
//			.roles("DEV");
		
		System.out.println("running details service");
		
		auth.userDetailsService(userDetailsService);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}

	// which users have access to which uri paths
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/**").authenticated() // all paths
			.and().httpBasic();
		
	}
	
}