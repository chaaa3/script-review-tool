package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.model.Status;
import com.example.scriptreviewtool.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;

	// Récupérer tous les statuts
	public List<Status> getAllStatuses() {
		return statusRepository.findAll();
	}

	// Récupérer un statut par nom
	public Optional<Status> getStatusByName(String name) {
		return statusRepository.findByName(name);
	}

	// Créer un nouveau statut
	public Status createStatus(Status status) {
		return statusRepository.save(status);
	}

	// Mettre à jour un statut existant
	public Status updateStatus(String name, Status updatedStatus) {
		return statusRepository.findByName(name).map(status -> {
			status.setName(updatedStatus.getName());
			return statusRepository.save(status);
		}).orElseThrow(() -> new RuntimeException("Statut introuvable avec le nom : " + name));
	}

	// Supprimer un statut
	public void deleteStatus(String name) {
		statusRepository.deleteByName(name);
	}
}