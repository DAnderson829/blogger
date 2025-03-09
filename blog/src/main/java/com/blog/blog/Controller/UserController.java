package com.blog.blog.Controller;

import com.blog.blog.Entity.Blog;
import com.blog.blog.Entity.User;
import com.blog.blog.Service.AuthService;
import com.blog.blog.Service.BlogService;
import com.blog.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/profile/{username}")
    public ResponseEntity<List<Blog>> findUserProfile(@PathVariable String username) {
        Optional<User> optionalUser = userService.findUserByUsername(username);

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        User user = optionalUser.get();

        List<Blog> blogs = blogService.getAllBlogsByUserId(user.getUserId());

        if(blogs.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(blogs);
}
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long Id){
        Optional<User> optionalUser = userService.findUserById(Id);

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        User user = optionalUser.get();

        userService.deleteUser(user.getUserId());
        return ResponseEntity.ok("User successfully deleted");
    }

    @PutMapping("/update/username/{Id}")
    public ResponseEntity<String> updateUsername(@PathVariable Long Id, @RequestParam String updatedUsername){
        Optional<User> optionalUser = userService.findUserById(Id);

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        User user = optionalUser.get();

        String updateResponse = userService.updateUsername(user.getUserId(), updatedUsername);
        return ResponseEntity.ok(updateResponse);
    }

    @PutMapping("/update/password/{Id}")
    public ResponseEntity<String> updatePassword(@PathVariable Long Id, @RequestParam String updatedPassword){
        Optional<User> optionalUser = userService.findUserById(Id);

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        User user = optionalUser.get();

        String updateResponse = userService.updatePassword(user.getUserId(), updatedPassword);
        return ResponseEntity.ok(updateResponse);
    }

}
