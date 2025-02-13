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

import com.example.scriptreviewtool.model.Status;
import com.example.scriptreviewtool.service.StatusService;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

	@Autowired
	private StatusService statusService;

	// Récupérer tous les statuts
	@GetMapping
	public ResponseEntity<List<Status>> getAllStatuses() {
		return ResponseEntity.ok(statusService.getAllStatuses());
	}

	// Récupérer un statut par nom
	@GetMapping("/{name}")
	public ResponseEntity<Status> getStatusByName(@PathVariable String name) {
		return statusService.getStatusByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Créer un nouveau statut
	@PostMapping
	public ResponseEntity<Status> createStatus(@RequestBody Status status) {
		return ResponseEntity.status(201).body(statusService.createStatus(status));
	}

	// Mettre à jour un statut existant
	@PutMapping("/{name}")
	public ResponseEntity<Status> updateStatus(@PathVariable String name, @RequestBody Status updatedStatus) {
		try {
			Status updated = statusService.updateStatus(name, updatedStatus);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Supprimer un statut
	@DeleteMapping("/{name}")
	public ResponseEntity<Void> deleteStatus(@PathVariable String name) {
		statusService.deleteStatus(name);
		return ResponseEntity.noContent().build();
	}
}