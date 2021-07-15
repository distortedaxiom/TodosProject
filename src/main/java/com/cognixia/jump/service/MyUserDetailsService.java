package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userFound = userRepo.findByUsername(username);	
		
		if( !userFound.isPresent() ) { // if NOT found
			System.out.println(username);
			throw new UsernameNotFoundException("No user with username: " + username);
		}
		
		return new MyUserDetails(userFound.get()); 
	}

}
