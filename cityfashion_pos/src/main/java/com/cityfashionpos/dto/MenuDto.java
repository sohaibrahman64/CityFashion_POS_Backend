package com.cityfashionpos.dto;

import java.util.List;

/**
 * @author User
 *
 */
public class MenuDto {
	private Long id;
	private String name;
	private String path;
	private String icon;
	private String filename;
	List<MenuDto> children;

	public MenuDto() {
	}

	public MenuDto(Long id, String name, String path, String icon, String filename) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.icon = icon;
		this.filename = filename;
	}

	public MenuDto(Long id, String name, String path, String icon) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.icon = icon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

}
