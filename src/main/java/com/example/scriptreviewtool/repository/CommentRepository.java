package com.example.scriptreviewtool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Comment;
import com.example.scriptreviewtool.model.Script;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByScript(Script script);
}