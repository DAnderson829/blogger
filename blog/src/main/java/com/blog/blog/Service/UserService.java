package com.blog.blog.Service;

import com.blog.blog.Entity.User;
import com.blog.blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    public String createUser(String username, String password){
        if(userRepository.findByUsername(username).isPresent()){
            return "Username already exists.";
        }

        if(username.length() < 5){
            return "Username must be at least 5 characters long.";
        }

        if(password.length() < 6){
            return "Password is too short.";
        }
        String encodedPassword = passwordService.encodePassword(password);
        User user = new User(username, encodedPassword);
        userRepository.save(user);
        return "User successfully created";
    }

    public String login(String username, String password){
        if(userRepository.findByUsername(username).isEmpty()){
            return "Username not found.";
        }

        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();

        if(!passwordService.matches(password, user.getPassword())){
            return "Invalid Password.";
        }

        return "Login successful.";
    }

    public boolean deleteUser(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String updateUsername(Long Id, String username){
        if(userRepository.findByUsername(username).isPresent()){
            return "Username already exists.";
        }

        if(username.length() < 5){
            return "Username must be at least 5 characters long.";
        }


        Optional<User> optionalUser = userRepository.findById(Id);
        User user = optionalUser.get();

        user.setUsername(username);

        userRepository.save(user);

        return "Username updated successfully.";
    }

    public String updatePassword(Long Id, String password){
        if(password.length() < 6){
            return "Password is too short.";
        }

        Optional<User> optionalUser = userRepository.findById(Id);
        User user = optionalUser.get();

        String encodedPassword = passwordService.encodePassword(password);

        user.setPassword(encodedPassword);
        userRepository.save(user);

        return "Password updated successfully.";
    }
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


}
