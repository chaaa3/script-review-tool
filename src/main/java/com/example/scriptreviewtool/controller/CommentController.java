package com.example.scriptreviewtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptreviewtool.dto.CommentDTO;
import com.example.scriptreviewtool.model.Comment;
import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/script/{scriptId}")
	public List<CommentDTO> getCommentsByScript(@PathVariable Long scriptId) {
		// Récupérer le script depuis le repository ou service
		Script script = new Script(); // Remplacez par une méthode réelle
		return commentService.getCommentsByScript(script);
	}

	@PostMapping
	public CommentDTO addComment(@RequestBody Comment comment) {
		return commentService.addComment(comment);
	}
}