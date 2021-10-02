package com.airtnt.backend.security;

import com.airtnt.backend.user.UserRepository;
import com.airtnt.common.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class testEncoderpassword {
    @Autowired UserRepository repo;
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Test
    public void encodePassword(){
        
        User user = repo.findById(4).get();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
    }
}
