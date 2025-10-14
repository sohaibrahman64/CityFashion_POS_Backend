package com.cityfashionpos.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.MenuDto;
import com.cityfashionpos.entity.MenuEntity;
import com.cityfashionpos.entity.MenuNewEntity;
import com.cityfashionpos.repository.MenuNewRepository;
import com.cityfashionpos.repository.MenuRepository;
import com.cityfashionpos.repository.PermissionRepository;
import com.cityfashionpos.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private MenuNewRepository menuNewRepository;

	public MenuServiceImpl(MenuRepository menuRepository, PermissionRepository permissionRepository,
			MenuNewRepository menuNewRepository) {
		this.menuRepository = menuRepository;
		this.permissionRepository = permissionRepository;
		this.menuNewRepository = menuNewRepository;
	}

	private List<MenuDto> buildMenuTree(List<MenuEntity> flatList) {
		Map<Long, MenuDto> dtoMap = new HashMap<>();
		List<MenuDto> rootItems = new ArrayList<>();

		// Step 1: Convert to DTOs and map by ID
		for (MenuEntity menu : flatList) {
			MenuDto dto = new MenuDto(
					menu.getId(),
					menu.getName(),
					menu.getPath(),
					menu.getIcon());
			dtoMap.put(menu.getId(), dto);
		}

		// Step 2: Set children based on parent ID
		for (MenuEntity menu : flatList) {
			MenuDto dto = dtoMap.get(menu.getId());
			if (menu.getParent() != null && dtoMap.containsKey(menu.getParent().getId())) {
				MenuDto parentDto = dtoMap.get(menu.getParent().getId());
				if (parentDto.getChildren() == null)
					parentDto.setChildren(new ArrayList<>());
				parentDto.getChildren().add(dto);
			} else {
				rootItems.add(dto);
			}
		}

		return rootItems;
	}

	@Override
	public List<MenuDto> getMenuByRoleId(Long roleId) {
		// 1. Get viewable menu IDs for the role
		List<MenuEntity> allowedMenus = permissionRepository.findMenusByRoleId(roleId);

		// 2. Load only the menus that match those IDs
		// List<MenuEntity> allowedMenus = (List<MenuEntity>)
		// menuRepository.findAllById(allowedMenuIds);

		// 3. Build hierarchical tree
		return buildMenuTree(allowedMenus);
	}

	@Override
	public List<MenuDto> getAllActiveMenus() {
		// Get all active menus from menu_new table (where is_active = 1)
		List<MenuNewEntity> activeMenus = menuNewRepository.findAllActiveMenus();

		// Build hierarchical tree
		return buildMenuTreeFromMenuNew(activeMenus);
	}

	private List<MenuDto> buildMenuTreeFromMenuNew(List<MenuNewEntity> flatList) {
		Map<Long, MenuDto> dtoMap = new HashMap<>();
		List<MenuDto> rootItems = new ArrayList<>();

		// Step 1: Convert to DTOs and map by ID
		for (MenuNewEntity menu : flatList) {
			MenuDto dto = new MenuDto(
					menu.getId(),
					menu.getName(),
					menu.getPath(),
					menu.getIcon(),
					menu.getFilename());
			dtoMap.put(menu.getId(), dto);
		}

		// Step 2: Set children based on parent ID
		for (MenuNewEntity menu : flatList) {
			MenuDto dto = dtoMap.get(menu.getId());
			if (menu.getParent() != null && dtoMap.containsKey(menu.getParent().getId())) {
				MenuDto parentDto = dtoMap.get(menu.getParent().getId());
				if (parentDto.getChildren() == null)
					parentDto.setChildren(new ArrayList<>());
				parentDto.getChildren().add(dto);
			} else {
				rootItems.add(dto);
			}
		}

		return rootItems;
	}

}
