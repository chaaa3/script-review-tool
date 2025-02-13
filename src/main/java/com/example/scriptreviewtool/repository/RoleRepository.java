package com.example.scriptreviewtool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	// Rechercher un rôle par nom
	Optional<Role> findByName(String name);
}