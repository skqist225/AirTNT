package com.airtnt.frontend.user;

import com.airtnt.common.entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE User u SET u.phoneVerified = true WHERE u.id = ?1")
    public int verifyPhoneNumber(Integer userId);

}
