package com.cityfashionpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.LoginRequest;
import com.cityfashionpos.dto.UserDto;
import com.cityfashionpos.service.LoginService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // allow frontend access
public class LoginController {
	
	private final LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        UserDto user = loginService.login(request);
        return ResponseEntity.ok(user);
    }
	

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//		String username = loginRequest.getUsername();
//		String password = loginRequest.getPassword();
//		String secretKey = configUtils.getSecretKey();
//
//		Optional<UserEntity> userOpt = userRepository.authenticateUser(username, password, secretKey);
//
//		if (userOpt.isPresent()) {
//			UserEntity user = userOpt.get();
//			return ResponseEntity
//					.ok(Map.of("message", "Login successful", "username", username, "role", user.getRole().getName()));
//		} else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
//		}
//
//		// 🔐 Replace with real DB check
////        if ("admin".equals(username) && "password".equals(password)) {
////            return new LoginResponse(true, "Login successful");
////        } else {
////            return new LoginResponse(false, "Invalid username or password");
////        }
//	}
}
