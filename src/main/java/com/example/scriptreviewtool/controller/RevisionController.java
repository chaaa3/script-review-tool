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

import com.example.scriptreviewtool.model.Revision;
import com.example.scriptreviewtool.service.RevisionService;

@RestController
@RequestMapping("/api/revisions")
public class RevisionController {

	@Autowired
	private RevisionService revisionService;

	// Récupérer toutes les révisions
	@GetMapping
	public ResponseEntity<List<Revision>> getAllRevisions() {
		return ResponseEntity.ok(revisionService.getAllRevisions());
	}

	// Récupérer une révision par ID
	@GetMapping("/{id}")
	public ResponseEntity<Revision> getRevisionById(@PathVariable Long id) {
		return revisionService.getRevisionById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Créer une nouvelle révision
	@PostMapping
	public ResponseEntity<Revision> createRevision(@RequestBody Revision revision) {
		return ResponseEntity.status(201).body(revisionService.createRevision(revision));
	}

	// Mettre à jour une révision existante
	@PutMapping("/{id}")
	public ResponseEntity<Revision> updateRevision(@PathVariable Long id, @RequestBody Revision updatedRevision) {
		try {
			Revision updated = revisionService.updateRevision(id, updatedRevision);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Supprimer une révision
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRevision(@PathVariable Long id) {
		revisionService.deleteRevision(id);
		return ResponseEntity.noContent().build();
	}
}