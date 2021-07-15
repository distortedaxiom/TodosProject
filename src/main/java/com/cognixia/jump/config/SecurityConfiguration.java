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

import com.cognixia.jump.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	 MyUserDetailsService MyUserDetailsService; 
	
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {

		auth.userDetailsService(MyUserDetailsService).passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/users").permitAll().and()
        .authorizeRequests()
        .antMatchers("/api/**").authenticated().and().httpBasic();
        
	}
	
}