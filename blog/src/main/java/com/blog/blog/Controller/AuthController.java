package com.blog.blog.Controller;

import com.blog.blog.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestParam String username, @RequestParam String password) {
        String response = authService.login(username, password);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegistration(@RequestParam String username, @RequestParam String password) {
        String response = authService.registerUser(username, password);
        return ResponseEntity.ok(response);
    }
}
