package com.example.scriptreviewtool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

	// Rechercher un statut par nom
	Optional<Status> findByName(String name);

	// Supprimer un statut par nom
	void deleteByName(String name);
}