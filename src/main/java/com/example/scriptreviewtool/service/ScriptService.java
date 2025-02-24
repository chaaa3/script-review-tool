package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.dto.ScriptDTO;
import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.repository.ScriptRepository;

import ch.qos.logback.core.status.Status;

@Service
public class ScriptService {

	@Autowired
	private ScriptRepository scriptRepository;

	public List<ScriptDTO> getAllScripts() {
		return scriptRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public ScriptDTO createScript(Script script) {
		script.setCreatedAt(new java.util.Date());
		script.setStatus(Status.UNDER_REVIEW);
		return convertToDTO(scriptRepository.save(script));
	}

	public ScriptDTO updateScript(Long id, Script updatedScript) {
		return scriptRepository.findById(id).map(script -> {
			script.setTitle(updatedScript.getTitle());
			script.setContent(updatedScript.getContent());
			script.setUpdatedAt(new java.util.Date());
			return convertToDTO(scriptRepository.save(script));
		}).orElseThrow(() -> new RuntimeException("Script not found"));
	}

	public void deleteScript(Long id) {
		scriptRepository.deleteById(id);
	}

	private ScriptDTO convertToDTO(Script script) {
		ScriptDTO dto = new ScriptDTO();
		dto.setId(script.getId());
		dto.setTitle(script.getTitle());
		dto.setContent(script.getContent());
		dto.setStatus(script.getStatus().name());
		dto.setAuthorUsername(script.getAuthor().getUsername());
		dto.setCreatedAt(script.getCreatedAt());
		dto.setUpdatedAt(script.getUpdatedAt());
		return dto;
	}
}