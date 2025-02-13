package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.repository.ScriptRepository;

@Service
public class ScriptService {

	@Autowired
	private ScriptRepository scriptRepository;

	// Récupérer tous les scripts
	public List<Script> getAllScripts() {
		return scriptRepository.findAll();
	}

	// Récupérer un script par ID
	public Optional<Script> getScriptById(Long id) {
		return scriptRepository.findById(id);
	}

	// Créer un nouveau script
	public Script createScript(Script script) {
		return scriptRepository.save(script);
	}

	// Mettre à jour un script existant
	public Script updateScript(Long id, Script updatedScript) {
		return scriptRepository.findById(id).map(script -> {
			script.setTitle(updatedScript.getTitle());
			script.setContent(updatedScript.getContent());
			script.setReviewers(updatedScript.getReviewers());
			script.setStatus(updatedScript.getStatus());
			return scriptRepository.save(script);
		}).orElseThrow(() -> new RuntimeException("Script introuvable avec l'ID : " + id));
	}

	// Supprimer un script
	public void deleteScript(Long id) {
		scriptRepository.deleteById(id);
	}
}