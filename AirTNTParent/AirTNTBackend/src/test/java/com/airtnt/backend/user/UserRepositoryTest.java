package com.airtnt.backend.user;

import com.airtnt.common.entity.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

        @Autowired
        private UserRepository userRepository;

        @Autowired EntityManager entityManage;

        @SneakyThrows
        @Test
        public void testAddUser() {
                DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                Date birthday = dateFormatter.parse("22-05-2000");

                State state = new State(1);
                City city = new City(1);
                Country country = new Country(1);

                Address address = Address.builder().aprtNoAndStreet("121/16/20 DX06").city(city).state(state)
                                .country(country).build();

                Role host = Role.builder().name("HOST").build();

                User 陶泉御所坊 = User.builder().about("Tôi tên là 陶泉 御所坊").birthday(birthday).address(address)
                                .avatar("avatar.png").email("kongngoxilau@gmail.com").firstName("陶泉")
                                .password("12345678").lastName("御所坊").sex(Sex.MALE).role(host).phoneNumber("0353996236")
                                .build();

                userRepository.save(陶泉御所坊);
        }

        @Test
        public void testAddAnotherUser() throws ParseException {
                DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                Date birthday = dateFormatter.parse("22-05-2000");

                State state = new State(1);
                City city = new City(1);
                Country country = new Country(1);

                Address address = Address.builder().AprtNoAndStreet("121/16/5 DX06").city(city).state(state)
                                .country(country).build();

                Role host = entityManage.find(Role.class, 1);

                User 陶泉御所坊 = User.builder().about("Tôi tên là 陶泉 御所坊").birthday(birthday).address(address)
                                .avatar("avatar.png").email("kongngoxilau1@gmail.com").firstName("陶泉")
                                .password("12345678").lastName("御所坊").sex(Sex.MALE).role(host).phoneNumber("0353996236")
                                .build();

                userRepository.save(陶泉御所坊);
        }

        @Test
        public void testAddManyUser() throws ParseException {
                DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                Date birthday = dateFormatter.parse("22-05-2000");

                State state = new State(1);
                City city = new City(1);
                Country country = new Country(1);

                Address address = Address.builder().AprtNoAndStreet("121/16/21 DX06").city(city).state(state)
                                .country(country).build();
                Address address2 = Address.builder().AprtNoAndStreet("121/16/22 DX06").city(city).state(state)
                                .country(country).build();
                Address address3 = Address.builder().AprtNoAndStreet("121/16/23 DX06").city(city).state(state)
                                .country(country).build();
                Address address4 = Address.builder().AprtNoAndStreet("121/16/24 DX06").city(city).state(state)
                                .country(country).build();

                Role host = entityManage.find(Role.class, 1);

                User 陶泉御所坊 = User.builder().about("Tôi tên là 陶泉 御所坊").birthday(birthday).address(address)
                                .avatar("avatar.png").email("kongngoxilau3@gmail.com").firstName("陶泉")
                                .password("12345678").lastName("御所坊").sex(Sex.MALE).role(host).phoneNumber("0353996236")
                                .build();

                User 陶泉御所坊2 = User.builder().about("Tôi tên là 陶泉 御所坊").birthday(birthday).address(address2)
                                .avatar("avatar.png").email("kongngoxilau4@gmail.com").firstName("陶泉")
                                .password("12345678").lastName("御所坊").sex(Sex.MALE).role(host).phoneNumber("0353996236")
                                .build();

                User 陶泉御所坊3 = User.builder().about("Tôi tên là 陶泉 御所坊").birthday(birthday).address(address3)
                                .avatar("avatar.png").email("kongngoxilau5@gmail.com").firstName("陶泉")
                                .password("12345678").lastName("御所坊").sex(Sex.MALE).role(host).phoneNumber("0353996236")
                                .build();

                User 陶泉御所坊4 = User.builder().about("Tôi tên là 陶泉 御所坊").birthday(birthday).address(address4)
                                .avatar("avatar.png").email("kongngoxilau6@gmail.com").firstName("陶泉")
                                .password("12345678").lastName("御所坊").sex(Sex.MALE).role(host).phoneNumber("0353996236")
                                .build();

                userRepository.saveAll(List.of(陶泉御所坊, 陶泉御所坊2, 陶泉御所坊3, 陶泉御所坊4));

        }


        @Test
        public void testGetUser(){
                Optional<User> user = userRepository.findById(1);
                assertThat(user.isPresent());
        }

        @Test
        public void tesFindUserByEmail(){
                User user = userRepository.findByEmail("kongngoxilau@gmail.com");
                assertThat(user).isNotNull();
        }
}