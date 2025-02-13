package com.example.scriptreviewtool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Revision;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

	// Rechercher toutes les révisions d'un script spécifique
	List<Revision> findByScript_Id(Long scriptId);

	// Rechercher toutes les révisions d'un utilisateur spécifique
	List<Revision> findByAuthor_Id(Long authorId);
}