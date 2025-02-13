package com.example.scriptreviewtool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Script;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

	// Rechercher tous les scripts d'un auteur spécifique
	List<Script> findByAuthor_Id(Long authorId);

	// Rechercher tous les scripts ayant un statut spécifique
	List<Script> findByStatus_Name(String statusName);
}