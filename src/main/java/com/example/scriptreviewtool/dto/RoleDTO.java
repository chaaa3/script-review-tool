package com.example.scriptreviewtool.dto;

import lombok.Data;

@Data
public class RoleDTO {

	private String name;

	// Constructeur pour mapper une entité Role vers un RoleDTO
	public RoleDTO(com.example.scriptreviewtool.model.Role role) {
		this.name = role.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}