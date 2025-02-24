package com.example.scriptreviewtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scriptreviewtool.dto.ScriptDTO;
import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.service.ScriptService;

@RestController
@RequestMapping("/api/scripts")
public class ScriptController {

	@Autowired
	private ScriptService scriptService;

	@GetMapping
	public List<ScriptDTO> getAllScripts() {
		return scriptService.getAllScripts();
	}

	@PostMapping
	public ScriptDTO createScript(@RequestBody Script script) {
		return scriptService.createScript(script);
	}

	@PutMapping("/{id}")
	public ScriptDTO updateScript(@PathVariable Long id, @RequestBody Script updatedScript) {
		return scriptService.updateScript(id, updatedScript);
	}

	@DeleteMapping("/{id}")
	public void deleteScript(@PathVariable Long id) {
		scriptService.deleteScript(id);
	}
}