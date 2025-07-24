package com.cityfashionpos.dto;

public class UserDto {
	private Long id;
	private String username;
	private String role;
	private Long roleId;
	private String name;
	private boolean success;
	private String message;

	public UserDto() {
	}

	public UserDto(Long id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
	}

	public UserDto(Long id, String username, Long roleId) {
		this.id = id;
		this.username = username;
		this.roleId = roleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
