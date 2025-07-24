package com.cityfashionpos.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.MenuDto;
import com.cityfashionpos.entity.MenuEntity;
import com.cityfashionpos.repository.MenuRepository;
import com.cityfashionpos.repository.PermissionRepository;
import com.cityfashionpos.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	
	
	public MenuServiceImpl(MenuRepository menuRepository, PermissionRepository permissionRepository) {
		this.menuRepository = menuRepository;
		this.permissionRepository = permissionRepository;
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
	            menu.getIcon()
	        );
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
	    //List<MenuEntity> allowedMenus = (List<MenuEntity>) menuRepository.findAllById(allowedMenuIds);

	    // 3. Build hierarchical tree
	    return buildMenuTree(allowedMenus);
	}


	

}
