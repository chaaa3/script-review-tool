package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.dto.CommentDTO;
import com.example.scriptreviewtool.model.Comment;
import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<CommentDTO> getCommentsByScript(Script script) {
		return commentRepository.findByScript(script).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public CommentDTO addComment(Comment comment) {
		comment.setCreatedAt(new java.util.Date());
		return convertToDTO(commentRepository.save(comment)); 
	}

	private CommentDTO convertToDTO(Comment comment) {
		CommentDTO dto = new CommentDTO();
		dto.setId(comment.getId());
		dto.setContent(comment.getContent());
		dto.setUserUsername(comment.getUser().getUsername());
		dto.setCreatedAt(comment.getCreatedAt());
		return dto;
	}
}