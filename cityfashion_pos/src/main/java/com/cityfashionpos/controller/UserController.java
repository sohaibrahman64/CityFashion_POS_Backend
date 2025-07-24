package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.UserDto;
import com.cityfashionpos.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
        this.userService = userService;
    }
	
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {

        UserDto newUser = userService.createUser(username, password, role);
        return ResponseEntity.ok(newUser);
    }


}
