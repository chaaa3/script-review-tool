package com.example.scriptreviewtool.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Script {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;

	@ManyToOne
	private User author;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToMany
	private List<User> reviewers = new ArrayList<>();

	@OneToMany(mappedBy = "script", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "script", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Revision> revisions = new ArrayList<>();

	private LocalDateTime createdAt = LocalDateTime.now();

	public Script() {

	}

	public Script(Long id, String title, String content, User author, Status status, LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.status = status;
		this.createdAt = createdAt;
	}

	// Getters and setters
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<User> getReviewers() {
		return reviewers;
	}

	public void setReviewers(List<User> reviewers) {
		this.reviewers = reviewers;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// Helper methods
	public void addReviewer(User reviewer) {
		reviewers.add(reviewer);
	}

	public void removeReviewer(User reviewer) {
		reviewers.remove(reviewer);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setScript(this);
	}

	public void addRevision(Revision revision) {
		revisions.add(revision);
		revision.setScript(this);
	}
}