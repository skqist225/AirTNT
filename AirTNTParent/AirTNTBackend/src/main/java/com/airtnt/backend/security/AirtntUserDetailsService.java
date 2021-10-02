package com.airtnt.backend.security;

import com.airtnt.backend.user.UserRepository;
import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AirtntUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		if(user!=null) {
			return new AirtntUserDetails(user);
		}
		throw new UsernameNotFoundException("Could not find user with email: " + email);
	}

}