package com.airtnt.frontend.user;

import com.airtnt.common.entity.Role;
import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setRole(new Role(2));
        encodePassword(user);

        userRepository.save(user);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepository.findByEmail(email);

        if (userByEmail == null)
            return true;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) { // create
            if (userByEmail != null)
                return false;
        } else { // edit
            if (userByEmail.getId() != id) {
                return false;
            }
        }

        return true;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public User getCurrentUser(Integer userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }

    public User save(User user, String updatedField) {
        return userRepository.save(user);
    }

}
