package com.blog.blog;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password){

    }

    public boolean deleteUser(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUser(String username, String password){

    }

    public Optional<User> findUser(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findUser(String username){
        return userRepository.findByUsername(username);
    }


}
