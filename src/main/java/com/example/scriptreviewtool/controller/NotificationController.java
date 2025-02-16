package com.example.scriptreviewtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptreviewtool.model.Notification;
import com.example.scriptreviewtool.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification", description = "API pour la gestion des notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@Operation(summary = "Créer une nouvelle notification")
	@PostMapping
	public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
		return ResponseEntity.status(201).body(notificationService.createNotification(notification));
	}

	@Operation(summary = "Récupérer les notifications d'un utilisateur")
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
		return ResponseEntity.ok(notificationService.getUserNotifications(userId));
	}

	@Operation(summary = "Marquer une notification comme lue")
	@PutMapping("/{id}/read")
	public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
		return ResponseEntity.ok(notificationService.markAsRead(id));
	}

	@Operation(summary = "Supprimer une notification")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
		notificationService.deleteNotification(id);
		return ResponseEntity.noContent().build();
	}
}
