package com.airtnt.airtntapp.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testVerifyPhoneNumber() {
        int isUpdated = userRepository.verifyPhoneNumber(17);
        System.out.println(isUpdated);
    }
}
