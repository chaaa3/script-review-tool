package com.example.scriptreviewtool.model;

public enum Status {
    DRAFT,           // Brouillon initial
    SUBMITTED,       // Soumis pour révision
    IN_REVIEW,       // En cours de révision
    NEEDS_REVISION,  // Nécessite des modifications
    APPROVED,        // Approuvé
    REJECTED;        // Rejeté

    public boolean isFinal() {
        return this == APPROVED || this == REJECTED;
    }
}