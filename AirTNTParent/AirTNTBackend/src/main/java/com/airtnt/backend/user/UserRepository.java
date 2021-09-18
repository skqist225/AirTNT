package com.airtnt.backend.user;

import java.util.List;

import com.airtnt.common.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    public User findByEmail(String email);

    public Long countById(Integer id);

    @Query("SELECT u FROM User u WHERE CONCAT(u.id, '', u.email, ' ', u.firstName, ' ', u.lastName) LIKE %?1%")
    public Page<User> findAll(String keyword, Pageable pageable);

    @Query("UPDATE User u SET u.status = ?2 WHERE u.id=?1")
    public void updateStatus(Integer id, boolean status);

}
