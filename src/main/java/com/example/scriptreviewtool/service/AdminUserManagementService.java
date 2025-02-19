package com.example.scriptreviewtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.scriptreviewtool.dto.UserManagementDTO;
import com.example.scriptreviewtool.model.Role;
import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.model.User;
import com.example.scriptreviewtool.repository.RoleRepository;
import com.example.scriptreviewtool.repository.ScriptRepository;
import com.example.scriptreviewtool.repository.UserRepository;

@Service
public class AdminUserManagementService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ScriptRepository scriptRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public User createUser(UserManagementDTO userDTO) {
		// Vérifier si l'utilisateur existe déjà
		if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
			throw new RuntimeException("Username already exists");
		}

		// Récupérer le rôle
		Role role = roleRepository.findByName(userDTO.getRoleName())
				.orElseThrow(() -> new RuntimeException("Role not found: " + userDTO.getRoleName()));

		// Créer le nouvel utilisateur
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(role);

		// Sauvegarder l'utilisateur
		user = userRepository.save(user);

		// Assigner les scripts si présents
		if (userDTO.getAssignedScriptIds() != null && !userDTO.getAssignedScriptIds().isEmpty()) {
			assignScriptsToUser(user.getId(), userDTO.getAssignedScriptIds());
		}

		return user;
	}

	@Transactional
	public User updateUser(Long userId, UserManagementDTO userDTO) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Mettre à jour les informations de base
		user.setEmail(userDTO.getEmail());
		if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		}

		// Mettre à jour le rôle si nécessaire
		if (userDTO.getRoleName() != null) {
			Role newRole = roleRepository.findByName(userDTO.getRoleName())
					.orElseThrow(() -> new RuntimeException("Role not found: " + userDTO.getRoleName()));
			user.setRole(newRole);
		}

		// Mettre à jour les scripts assignés
		if (userDTO.getAssignedScriptIds() != null) {
			assignScriptsToUser(userId, userDTO.getAssignedScriptIds());
		}

		return userRepository.save(user);
	}

	@Transactional
	public void assignScriptsToUser(Long userId, List<Long> scriptIds) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		List<Script> scripts = scriptRepository.findAllById(scriptIds);

		// Vérifier si tous les scripts existent
		if (scripts.size() != scriptIds.size()) {
			throw new RuntimeException("Some scripts were not found");
		}

		// Assigner les scripts à l'utilisateur
		scripts.forEach(script -> {
			script.setAuthor(user); // ou script.getReviewers().add(user) selon le rôle
		});

		scriptRepository.saveAll(scripts); // Sauvegarder les changements
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Transactional
	public User changeOwnPassword(Long userId, String currentPassword, String newPassword) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Vérifier si le mot de passe actuel est correct
		boolean isPasswordMatch = passwordEncoder.matches(currentPassword, user.getPassword());
		System.out.println("Password Match: " + isPasswordMatch);
		System.out.println("Stored Password Hash: " + user.getPassword());

		if (!isPasswordMatch) {
			throw new RuntimeException("Current password is incorrect");
		}

		// Hacher le nouveau mot de passe après la vérification
		user.setPassword(passwordEncoder.encode(newPassword));
		System.out.println("Current Password: " + currentPassword);
		System.out.println("User ID: " + userId);
		return userRepository.save(user);
	}
}