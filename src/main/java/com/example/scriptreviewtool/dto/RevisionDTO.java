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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}