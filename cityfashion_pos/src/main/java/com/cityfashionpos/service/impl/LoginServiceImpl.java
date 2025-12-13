package com.cityfashionpos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.LoginRequest;
import com.cityfashionpos.dto.UserDto;
import com.cityfashionpos.entity.UserEntity;
import com.cityfashionpos.repository.UserRepository;
import com.cityfashionpos.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	@Value("${app.secretkey}")
	private String secretKey;

	public LoginServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto login(LoginRequest request) {
		/*
		 * Optional<UserEntity> userOpt = userRepository.authenticateUser(
		 * request.getUsername(),
		 * request.getPassword(),
		 * secretKey
		 * );
		 */

		Optional<UserEntity> userOpt = userRepository.authenticateUser(
				request.getUsername(),
				request.getPassword());

		if (userOpt.isEmpty()) {
			throw new RuntimeException("Invalid username or password");
		}

		UserEntity user = userOpt.get();
		// UserDto userDto = new UserDto(user.getId(), user.getUsername(),
		// user.getRole().getId());
		UserDto userDto = new UserDto(user.getId(), user.getUsername());
		userDto.setMessage("Login successful");
		userDto.setSuccess(true);
		userDto.setName(user.getName());
		return userDto;
	}

}
