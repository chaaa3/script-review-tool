package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.model.User;
import com.example.scriptreviewtool.repository.UserRepository;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		logger.info("Creating user: {}", user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	// Mettre à jour un utilisateur existant
	public User updateUser(Long id, User updatedUser) {
		logger.info("Updating user with ID: {}", id);
		return userRepository.findById(id).map(user -> {
			user.setUsername(updatedUser.getUsername());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			user.setRole(updatedUser.getRole());
			return userRepository.save(user);
		}).orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));
	}

	// Supprimer un utilisateur
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	// Récupérer plusieurs utilisateurs par leurs IDs
	public List<User> getUsersByIds(List<Long> ids) {
		return userRepository.findAllById(ids);
	}
}