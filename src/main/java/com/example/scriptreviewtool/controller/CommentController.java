package com.example.scriptreviewtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptreviewtool.model.Comment;
import com.example.scriptreviewtool.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	// Récupérer tous les commentaires
	@GetMapping
	public ResponseEntity<List<Comment>> getAllComments() {
		return ResponseEntity.ok(commentService.getAllComments());
	}

	// Récupérer un commentaire par ID
	@GetMapping("/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
		return commentService.getCommentById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Créer un nouveau commentaire
	@PostMapping
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
		return ResponseEntity.status(201).body(commentService.createComment(comment));
	}

	// Mettre à jour un commentaire existant
	@PutMapping("/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
		try {
			Comment updated = commentService.updateComment(id, updatedComment);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Supprimer un commentaire
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.noContent().build();
	}
}