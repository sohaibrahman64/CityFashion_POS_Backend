package com.cityfashionpos.service;

import com.cityfashionpos.dto.LoginRequest;
import com.cityfashionpos.dto.UserDto;

public interface LoginService {

	UserDto login(LoginRequest request);

}
