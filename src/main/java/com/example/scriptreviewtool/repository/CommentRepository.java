package com.example.scriptreviewtool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	// Rechercher tous les commentaires d'un script spécifique
	List<Comment> findByScript_Id(Long scriptId);

	// Rechercher tous les commentaires d'un utilisateur spécifique
	List<Comment> findByAuthor_Id(Long authorId);

	// Rechercher tous les commentaires d'un script spécifique
	List<Comment> findByScriptId(Long scriptId);
}