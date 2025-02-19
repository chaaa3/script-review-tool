package com.example.scriptreviewtool.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptreviewtool.model.Notification;
import com.example.scriptreviewtool.model.NotificationType;
import com.example.scriptreviewtool.model.Revision;
import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.model.User;
import com.example.scriptreviewtool.repository.RevisionRepository;

@Service
@org.springframework.transaction.annotation.Transactional
public class RevisionService {

	@Autowired
	private RevisionRepository revisionRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private ScriptService scriptService;

	// Récupérer toutes les révisions
	public List<Revision> getAllRevisions() {
		return revisionRepository.findAll();
	}

	// Récupérer une révision par ID
	public Optional<Revision> getRevisionById(Long id) {
		return revisionRepository.findById(id);
	}

	// Récupérer toutes les révisions d'un script
	public List<Revision> getRevisionsByScriptId(Long scriptId) {
		return revisionRepository.findByScriptIdOrderByCreatedAtDesc(scriptId);
	}

	// Créer une nouvelle révision
	public Revision createRevision(Revision revision) {
		return revisionRepository.save(revision);
	}

	// Créer une nouvelle révision avancée
	public Revision createRevision(Long scriptId, String comment, User reviewer) {
		Script script = scriptService.getScriptById(scriptId)
				.orElseThrow(() -> new RuntimeException("Script not found"));

		Revision revision = new Revision();
		revision.setScript(script);
		revision.setAuthor(reviewer);
		revision.setChanges(comment);
		revision.setCreatedAt(LocalDateTime.now());

		// Notifier l'auteur du script
		Notification notification = new Notification();
		notification.setRecipient(script.getAuthor());
		notification.setMessage("Une nouvelle révision a été ajoutée à votre script '" + script.getTitle() + "'");
		notification.setType(NotificationType.REVISION_SUBMITTED.toString());
		notificationService.createNotification(notification);

		return revisionRepository.save(revision);
	}

	// Récupérer toutes les révisions d'un script par ordre décroissant de date
	public List<Revision> getRevisionsByScript(Long scriptId) {
		return revisionRepository.findByScriptIdOrderByCreatedAtDesc(scriptId);
	}

	// Récupérer les révisions d'un réviseur
	public List<Revision> getRevisionsByReviewer(Long reviewerId) {
		return revisionRepository.findAll().stream().filter(r -> r.getAuthor().getId().equals(reviewerId)).toList();
	}

	// Mettre à jour une révision existante
	public Revision updateRevision(Long id, Revision updatedRevision) {
		return revisionRepository.findById(id).map(revision -> {
			revision.setChanges(updatedRevision.getChanges());
			return revisionRepository.save(revision);
		}).orElseThrow(() -> new RuntimeException("Revision not found"));
	}

	// Supprimer une révision
	public void deleteRevision(Long id) {
		revisionRepository.deleteById(id);
	}
}