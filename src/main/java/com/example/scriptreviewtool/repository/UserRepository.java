package com.example.scriptreviewtool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Rechercher un utilisateur par email
	Optional<User> findByEmail(String email);

	// Rechercher tous les utilisateurs ayant un rôle spécifique
	List<User> findByRole_Name(String roleName);
}