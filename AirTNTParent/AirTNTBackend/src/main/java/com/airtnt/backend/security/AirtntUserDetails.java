package com.airtnt.backend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.airtnt.common.entity.Role;
import com.airtnt.common.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AirtntUserDetails implements UserDetails {

    private static final long serialVersionUID = 1;

    private User user;

    public AirtntUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = user.getRole();
        List<SimpleGrantedAuthority> authories = new ArrayList<>();

        authories.add(new SimpleGrantedAuthority(role.getName()));

        return authories;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
    public boolean isEnabled() {
        return user.isStatus();
    }

    public String getFullname() {
        return this.user.getFirstName() + " " + this.user.getLastName();
    }

    public Integer getId() {
        return this.user.getId();
    }

    public void setFirstName(String firstName) {
        this.user.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.user.setLastName(lastName);
    }

    public boolean hasRole(String roleName) {
        return user.hasRole(roleName);
    }
}
