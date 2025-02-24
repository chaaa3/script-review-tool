package com.example.scriptreviewtool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.model.User;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {
	List<Script> findByAuthor(User author);
}