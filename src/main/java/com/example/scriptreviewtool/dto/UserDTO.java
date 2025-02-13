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
}