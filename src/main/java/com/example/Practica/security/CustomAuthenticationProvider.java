package com.example.Practica.security;


import com.example.Practica.dto.AuthPayload;
import com.example.Practica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

//    private static List<String> userList =Arrays.asList("gxg@cst.ro");

    private UserRepository userRepository;

    @Autowired
    public void setServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Bean
    public Authentication authenticate(Authentication authentication) {
        String email = authentication.getName().trim();
        String inputPassword = authentication.getCredentials().toString();

        Optional<AuthPayload> optionalUser = userRepository.getByEmailWithPasswordAndRole(email);

        if (optionalUser.isPresent()) {

            AuthPayload authPayload = optionalUser.get();
            String dbPassword = authPayload.getPassword();

            if (BCrypt.checkpw(inputPassword, dbPassword)) {
                UserDetails userDetails = UserPrinciple.build(authPayload);
                Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);

                return newAuth;
            }
            throw new BadCredentialsException("Wrong password");

        }
        return null;
    }
}
