package com.example.scriptreviewtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.scriptreviewtool.model.Revision;
import com.example.scriptreviewtool.service.RevisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/revisions")
@Tag(name = "Revision", description = "API pour la gestion des révisions de scripts")
public class RevisionController {

    @Autowired
    private RevisionService revisionService;

    @Operation(summary = "Créer une nouvelle révision")
    @PostMapping
    public ResponseEntity<Revision> createRevision(@RequestBody Revision revision) {
        return ResponseEntity.status(201).body(revisionService.createRevision(revision));
    }

    @Operation(summary = "Récupérer l'historique des révisions d'un script")
    @GetMapping("/script/{scriptId}")
    public ResponseEntity<List<Revision>> getRevisionsByScriptId(@PathVariable Long scriptId) {
        return ResponseEntity.ok(revisionService.getRevisionsByScriptId(scriptId));
    }

    @Operation(summary = "Récupérer une révision spécifique")
    @GetMapping("/{id}")
    public ResponseEntity<Revision> getRevisionById(@PathVariable Long id) {
        return revisionService.getRevisionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Supprimer une révision")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevision(@PathVariable Long id) {
        revisionService.deleteRevision(id);
        return ResponseEntity.noContent().build();
    }
}