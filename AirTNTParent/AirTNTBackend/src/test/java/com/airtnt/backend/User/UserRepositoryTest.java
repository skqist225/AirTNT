package com.airtnt.backend.User;

import com.airtnt.backend.user.UserRepository;
import com.airtnt.common.entity.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

        @Autowired
        private UserRepository userRepository;

        @SneakyThrows
        @Test
        public void testAddUser() {
                DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                Date birthday = dateFormatter.parse("22-05-2000");

                State state = new State(1);
                City city = new City(1);
                Country country = new Country(1);

                Address address = Address.builder().AprtNoAndStreet("121/16/20 DX06").city(city).state(state)
                                .country(country).build();

                Role host = Role.builder().name("HOST").build();

                User 陶泉御所坊 = User.builder().about("Tôi tên là 陶泉 御所坊").birthday(birthday).address(address)
                                .avatar("avatar.png").email("kongngoxilau@gmail.com").firstName("陶泉")
                                .password("12345678").lastName("御所坊").sex(Sex.MALE).role(host).phoneNumber("0353996236")
                                .build();

                userRepository.save(陶泉御所坊);
        }
}