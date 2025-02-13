package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.model.Revision;
import com.example.scriptreviewtool.repository.RevisionRepository;

@Service
public class RevisionService {

    @Autowired
    private RevisionRepository revisionRepository;

    // Récupérer toutes les révisions
    public List<Revision> getAllRevisions() {
        return revisionRepository.findAll();
    }

    // Récupérer une révision par ID
    public Optional<Revision> getRevisionById(Long id) {
        return revisionRepository.findById(id);
    }

    // Créer une nouvelle révision
    public Revision createRevision(Revision revision) {
        return revisionRepository.save(revision);
    }

    // Mettre à jour une révision existante
    public Revision updateRevision(Long id, Revision updatedRevision) {
        return revisionRepository.findById(id)
                .map(revision -> {
                    revision.setChanges(updatedRevision.getChanges());
                    revision.setAuthor(updatedRevision.getAuthor());
                    revision.setScript(updatedRevision.getScript());
                    return revisionRepository.save(revision);
                }).orElseThrow(() -> new RuntimeException("Révision introuvable avec l'ID : " + id));
    }

    // Supprimer une révision
    public void deleteRevision(Long id) {
        revisionRepository.deleteById(id);
    }
}