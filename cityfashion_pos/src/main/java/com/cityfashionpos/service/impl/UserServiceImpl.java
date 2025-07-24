package com.cityfashionpos.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.UserDto;
import com.cityfashionpos.entity.RoleEntity;
import com.cityfashionpos.entity.UserEntity;
import com.cityfashionpos.repository.RoleRepository;
import com.cityfashionpos.repository.UserRepository;
import com.cityfashionpos.service.UserService;
import com.cityfashionpos.utils.AESUtil;


@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Value("${app.secretkey}")
	private String secretKey;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getRole().getName()))
                .collect(Collectors.toList());
    }
    
    
    @Override
    public UserDto createUser(String username, String rawPassword, String roleName) {
        Optional<RoleEntity> roleOpt = roleRepository.findByName(roleName);
        if (roleOpt.isEmpty()) {
            throw new RuntimeException("Role not found");
        }

        String encryptedPassword = AESUtil.encrypt(rawPassword, secretKey);
        String encoded = Base64.getEncoder().encodeToString(encryptedPassword.getBytes());

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encoded);
        user.setRole(roleOpt.get());

        UserEntity saved = userRepository.save(user);
        return new UserDto(saved.getId(), saved.getUsername(), saved.getRole().getName());
    }
    
}
