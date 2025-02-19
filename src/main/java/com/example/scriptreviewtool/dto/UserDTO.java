package com.example.scriptreviewtool.dto;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String username;
	private String email;
	private String role;

	public UserDTO(com.example.scriptreviewtool.model.User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		if (user.getRole() != null) {
			this.role = user.getRole().getName();
		}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}