package com.cognixia.jump.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognixia.jump.model.User;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	// attributes from User that are needed to do our authorization/authentication
	private String username;
	private String password;

	public MyUserDetails(User user) {

		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
		
	}
	
	public MyUserDetails() { }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new HashSet<GrantedAuthority>();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "MyUserDetails [username=" + username + ", password=" + password + "]";
	}
	
	

}