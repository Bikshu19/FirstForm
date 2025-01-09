package com.chandu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chandu.entity.User;
import com.chandu.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	    @PostMapping("/login")
	    public String login(@RequestBody User user) {
	        User existingUser = userRepository.findByUsername(user.getUsername());
	        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
	            return "Login successful!";
	        }
	        return "Invalid username or password!";
	    }

	    @PostMapping("/register")
	    public String register(@RequestBody User user) {
	        if (userRepository.findByUsername(user.getUsername()) != null) {
	            return "Username already exists!";
	        }
	        userRepository.save(user);
	        return "User registered successfully!";
	    }
	    @GetMapping("/user/{username}")
	    public User getUserDetails(@PathVariable String username) {
	        User user = userRepository.findByUsername(username);
	        if (user != null) {
	            return user;
	        }
	        return null; // or return an appropriate error message
	    }
	

}
