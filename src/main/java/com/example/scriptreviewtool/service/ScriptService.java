package com.example.scriptreviewtool.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.scriptreviewtool.model.Notification;
import com.example.scriptreviewtool.model.NotificationType;
import com.example.scriptreviewtool.model.Revision;
import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.model.Status;
import com.example.scriptreviewtool.model.User;
import com.example.scriptreviewtool.repository.ScriptRepository;

@Service
@Transactional
public class ScriptService {

	@Autowired
	private ScriptRepository scriptRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;

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
		script.setCreatedAt(LocalDateTime.now());
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

	// Fonctionnalités avancées pour la gestion des scripts
	public Script assignReviewers(Long scriptId, List<Long> reviewerIds) {
		Script script = scriptRepository.findById(scriptId).orElseThrow(() -> new RuntimeException("Script not found"));

		List<User> reviewers = userService.getUsersByIds(reviewerIds);
		script.setReviewers(reviewers);

		// Notifier les réviseurs assignés
		for (User reviewer : reviewers) {
			Notification notification = new Notification();
			notification.setRecipient(reviewer);
			notification.setMessage("Vous avez été assigné comme réviseur pour le script: " + script.getTitle());
			notification.setType(NotificationType.REVIEW_REQUESTED.toString());
			notificationService.createNotification(notification);
		}

		return scriptRepository.save(script);
	}

	public Script updateStatus(Long scriptId, Status newStatus, String comment) {
		Script script = scriptRepository.findById(scriptId).orElseThrow(() -> new RuntimeException("Script not found"));

		Status oldStatus = script.getStatus();
		script.setStatus(newStatus);

		// Créer une révision pour tracker le changement
		Revision revision = new Revision();
		revision.setScript(script);
		revision.setOldStatus(oldStatus);
		revision.setNewStatus(newStatus);
		revision.setComment(comment);
		revision.setCreatedAt(LocalDateTime.now());

		// Notifier l'auteur du changement de statut
		Notification notification = new Notification();
		notification.setRecipient(script.getAuthor());
		notification.setMessage("Le statut de votre script '" + script.getTitle() + "' a été changé de " + oldStatus
				+ " à " + newStatus);
		notification.setType(NotificationType.COMMENT_ADDED.toString());
		notificationService.createNotification(notification);

		return scriptRepository.save(script);
	}

	public List<Script> getScriptsByStatus(Status status) {
		return scriptRepository.findByStatus(status);
	}

	public List<Script> getScriptsByReviewer(Long reviewerId) {
		return scriptRepository.findByReviewersId(reviewerId);
	}

	public List<Script> getScriptsByAuthor(Long authorId) {
		return scriptRepository.findByAuthorId(authorId);
	}
}