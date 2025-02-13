package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.model.User;
import com.example.scriptreviewtool.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// Récupérer tous les utilisateurs
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Récupérer un utilisateur par ID
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	// Créer un nouvel utilisateur
	public User createUser(User user) {
		return userRepository.save(user);
	}

	// Mettre à jour un utilisateur existant
	public User updateUser(Long id, User updatedUser) {
		return userRepository.findById(id).map(user -> {
			user.setUsername(updatedUser.getUsername());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			user.setRole(updatedUser.getRole());
			return userRepository.save(user);
		}).orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));
	}

	// Supprimer un utilisateur
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}