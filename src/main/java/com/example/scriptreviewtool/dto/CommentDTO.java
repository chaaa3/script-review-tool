package com.example.scriptreviewtool.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {

	private Long id;
	private String text;
	private String authorUsername; // Nom d'utilisateur de l'auteur
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	// Constructeur pour mapper une entité Comment vers un CommentDTO
	public CommentDTO(com.example.scriptreviewtool.model.Comment comment) {
		this.id = comment.getId();
		this.text = comment.getText();
		if (comment.getAuthor() != null) {
			this.authorUsername = comment.getAuthor().getUsername();
		}
		this.createdAt = comment.getCreatedAt();
	}
}