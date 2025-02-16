package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.model.Comment;
import com.example.scriptreviewtool.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	// Récupérer tous les commentaires
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	// Récupérer un commentaire par ID
	public Optional<Comment> getCommentById(Long id) {
		return commentRepository.findById(id);
	}

	// Récupérer tous les commentaires d'un script
	public List<Comment> getCommentsByScriptId(Long scriptId) {
		return commentRepository.findByScriptId(scriptId);
	}

	// Créer un nouveau commentaire
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}

	// Mettre à jour un commentaire existant
	public Comment updateComment(Long id, Comment updatedComment) {
		return commentRepository.findById(id).map(comment -> {
			comment.setText(updatedComment.getText());
			comment.setAuthor(updatedComment.getAuthor());
			comment.setScript(updatedComment.getScript());
			return commentRepository.save(comment);
		}).orElseThrow(() -> new RuntimeException("Commentaire introuvable avec l'ID : " + id));
	}

	// Supprimer un commentaire
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}
}