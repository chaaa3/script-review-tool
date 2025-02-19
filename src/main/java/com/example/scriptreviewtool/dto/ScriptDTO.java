package com.example.scriptreviewtool.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.scriptreviewtool.model.Status;

import lombok.Data;

@Data
public class ScriptDTO {

	private Long id;
	private String title;
	private String content;
	private String authorUsername; // Nom d'utilisateur de l'auteur
	private List<String> reviewerUsernames;
	private com.example.scriptreviewtool.model.Status status; // Nom du statut
	private LocalDateTime createdAt;

	// Constructeur pour mapper une entité Script vers un ScriptDTO
	public ScriptDTO(com.example.scriptreviewtool.model.Script script) {
		this.id = script.getId();
		this.title = script.getTitle();
		this.content = script.getContent();
		if (script.getAuthor() != null) {
			this.authorUsername = script.getAuthor().getUsername();
		}
		if (script.getReviewers() != null) {
			this.reviewerUsernames = script.getReviewers().stream().map(user -> user.getUsername()).toList();
		}
		if (script.getStatus() != null) {
			this.status = (Status) script.getStatus();
		}
		this.createdAt = script.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public List<String> getReviewerUsernames() {
		return reviewerUsernames;
	}

	public void setReviewerUsernames(List<String> reviewerUsernames) {
		this.reviewerUsernames = reviewerUsernames;
	}

	public com.example.scriptreviewtool.model.Status getStatus() {
		return status;
	}

	public void setStatus(com.example.scriptreviewtool.model.Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}