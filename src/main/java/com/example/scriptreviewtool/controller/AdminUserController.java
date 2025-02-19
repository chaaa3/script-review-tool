package com.example.scriptreviewtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptreviewtool.dto.ChangePasswordRequest;
import com.example.scriptreviewtool.dto.UserManagementDTO;
import com.example.scriptreviewtool.model.User;
import com.example.scriptreviewtool.service.AdminUserManagementService;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')") // Seul l'admin peut accéder à ces endpoints
public class AdminUserController {

	@Autowired
	private AdminUserManagementService adminUserManagementService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserManagementDTO userDTO) {
		User newUser = adminUserManagementService.createUser(userDTO);
		return ResponseEntity.ok(newUser);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserManagementDTO userDTO) {
		User updatedUser = adminUserManagementService.updateUser(userId, userDTO);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		adminUserManagementService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = adminUserManagementService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User user = adminUserManagementService.getUserById(userId);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/{userId}/scripts")
	public ResponseEntity<Void> assignScripts(@PathVariable Long userId, @RequestBody List<Long> scriptIds) {
		adminUserManagementService.assignScriptsToUser(userId, scriptIds);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{userId}/change-password")
	public ResponseEntity<User> changeOwnPassword(@PathVariable Long userId,
			@RequestBody ChangePasswordRequest request) {
		User updatedUser = adminUserManagementService.changeOwnPassword(userId, request.getCurrentPassword(),
				request.getNewPassword());
		return ResponseEntity.ok(updatedUser);
	}
}
