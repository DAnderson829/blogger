package com.blog.blog.Service;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {

    @Autowired
    private UserService userService;

    public String registerUser(String username, String password){
        return userService.createUser(username, password);
    }

    public String
}
