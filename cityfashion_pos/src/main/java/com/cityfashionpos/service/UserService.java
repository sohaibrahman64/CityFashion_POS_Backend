package com.cityfashionpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.UserDto;

@Service
public interface UserService {
	List<UserDto> getAllUsers();
	UserDto createUser(String username, String rawPassword, String roleName);
}
