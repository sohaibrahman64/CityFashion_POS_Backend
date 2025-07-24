package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.MenuDto;
import com.cityfashionpos.service.MenuService;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuController {
	
	@Autowired
	private MenuService menuService;

	@GetMapping("/getMenuByRole")
	public List<MenuDto> getMenuByRole(@RequestParam Long roleId) {
		return menuService.getMenuByRoleId(roleId);
	}

}
