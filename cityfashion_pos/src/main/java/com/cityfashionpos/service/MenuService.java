package com.cityfashionpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.MenuDto;

@Service
public interface MenuService {
	List<MenuDto> getMenuByRoleId(Long roleId);

	/**
	 * Get all active menus from menu_new table without permission check
	 */
	List<MenuDto> getAllActiveMenus();
}
