package com.example.Practica.security;


import com.example.Practica.dto.AuthPayload;
import com.example.Practica.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {

    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(String email, Collection<? extends GrantedAuthority> authorities) {
        this.email=email;
        this.authorities=authorities;
    }

    public static UserPrinciple build(AuthPayload doctor) {
        System.out.println(doctor.getRole());
        String email = doctor.getEmail();
        List<GrantedAuthority> authorityList = new ArrayList<>();

        String role = doctor.getRole();


        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
        authorityList.add(simpleGrantedAuthority);

        return new UserPrinciple(email, authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

    @Override
    public String toString() {
        return "UserPrinciple{" +
                "email='" + email + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
