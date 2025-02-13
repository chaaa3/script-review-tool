package com.example.scriptreviewtool.dto;

import lombok.Data;

@Data
public class StatusDTO {

	private String name;

	// Constructeur pour mapper une entité Status vers un StatusDTO
	public StatusDTO(com.example.scriptreviewtool.model.Status status) {
		this.name = status.getName();
	}
}