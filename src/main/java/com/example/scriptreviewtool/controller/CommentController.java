package com.example.scriptreviewtool.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.scriptreviewtool.model.Comment;
import com.example.scriptreviewtool.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comment", description = "API pour la gestion des commentaires")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    // Récupérer tous les commentaires
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    // Récupérer un commentaire par ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouveau commentaire")
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        try {
            logger.info("Tentative de création d'un commentaire : {}", comment);
            if (comment.getAuthor() == null || comment.getAuthor().getId() == null) {
                logger.error("L'auteur ou l'ID de l'auteur est null");
                return ResponseEntity.badRequest().build();
            }
            if (comment.getScript() == null || comment.getScript().getId() == null) {
                logger.error("Le script ou l'ID du script est null");
                return ResponseEntity.badRequest().build();
            }
            if (comment.getText() == null || comment.getText().trim().isEmpty()) {
                logger.error("Le texte du commentaire est null ou vide");
                return ResponseEntity.badRequest().build();
            }
            Comment savedComment = commentService.createComment(comment);
            logger.info("Commentaire créé avec succès : {}", savedComment);
            return ResponseEntity.status(201).body(savedComment);
        } catch (Exception e) {
            logger.error("Erreur lors de la création du commentaire", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Récupérer tous les commentaires d'un script")
    @GetMapping("/script/{scriptId}")
    public ResponseEntity<List<Comment>> getCommentsByScriptId(@PathVariable Long scriptId) {
        return ResponseEntity.ok(commentService.getCommentsByScriptId(scriptId));
    }

    // Mettre à jour un commentaire existant
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        try {
            Comment updated = commentService.updateComment(id, updatedComment);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un commentaire")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}