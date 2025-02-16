package com.example.scriptreviewtool.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RevisionDTO {

	private Long id;
	private String changes;
	private String authorUsername; // Nom d'utilisateur de l'auteur
	private LocalDateTime createdAt;

	// Constructeur pour mapper une entité Revision vers un RevisionDTO
	public RevisionDTO(com.example.scriptreviewtool.model.Revision revision) {
		this.id = revision.getId();
		this.changes = revision.getChanges();
		if (revision.getAuthor() != null) {
			this.authorUsername = revision.getAuthor().getUsername();
		}
		this.createdAt = revision.getCreatedAt();
	}
}