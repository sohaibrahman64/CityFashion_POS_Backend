package com.cityfashionpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.MenuDto;

@Service
public interface MenuService {
	List<MenuDto> getMenuByRoleId(Long roleId);
}
