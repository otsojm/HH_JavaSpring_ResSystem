package com.soft.ressystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.soft.ressystem.domain.User;
import com.soft.ressystem.domain.UserRepository;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword("123");
        user.setRole("USER");
        user.setEmail(connection.getDisplayName().toString().split(" ")[0] + "." + connection.getDisplayName().toString().split(" ")[1] + "@nummipallo.fi");
        user.setCustomertype("Adult");
        user.setPricecategory(8.0);
        userRepository.save(user);
        return user.getUsername();
    }
}