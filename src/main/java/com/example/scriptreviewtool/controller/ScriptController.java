package com.example.scriptreviewtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.service.ScriptService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/scripts")
@Tag(name = "Script", description = "API pour la gestion des scripts")
public class ScriptController {

    @Autowired
    private ScriptService scriptService;

    @Operation(summary = "Récupérer tous les scripts")
    @ApiResponse(
        responseCode = "200",
        description = "Liste des scripts récupérée avec succès",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Script.class))
        )
    )
    @GetMapping
    public ResponseEntity<List<Script>> getAllScripts() {
        return ResponseEntity.ok(scriptService.getAllScripts());
    }

    @Operation(summary = "Récupérer un script par son ID")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Script trouvé",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Script.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Script non trouvé",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Script> getScriptById(
            @Parameter(description = "ID du script à récupérer") 
            @PathVariable Long id) {
        return scriptService.getScriptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouveau script")
    @ApiResponse(
        responseCode = "201",
        description = "Script créé avec succès",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Script.class))
    )
    @PostMapping
    public ResponseEntity<Script> createScript(
            @Parameter(description = "Script à créer") 
            @RequestBody @Valid Script script) {
        return ResponseEntity.status(201).body(scriptService.createScript(script));
    }

    @Operation(summary = "Mettre à jour un script existant")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Script mis à jour avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Script.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Script non trouvé",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Script> updateScript(
            @Parameter(description = "ID du script à mettre à jour") 
            @PathVariable Long id,
            @Parameter(description = "Nouvelles données du script") 
            @RequestBody @Valid Script script) {
        return ResponseEntity.ok(scriptService.updateScript(id, script));
    }

    @Operation(summary = "Supprimer un script")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Script supprimé avec succès",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Script non trouvé",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScript(
            @Parameter(description = "ID du script à supprimer") 
            @PathVariable Long id) {
        scriptService.deleteScript(id);
        return ResponseEntity.noContent().build();
    }
}