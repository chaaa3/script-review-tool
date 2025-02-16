package com.example.scriptreviewtool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.scriptreviewtool.model.Notification;
import com.example.scriptreviewtool.repository.NotificationRepository;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	public Notification createNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	public List<Notification> getUserNotifications(Long userId) {
		return notificationRepository.findByRecipientId(userId);
	}

	public List<Notification> getUnreadNotifications(Long userId) {
		return notificationRepository.findByRecipientIdAndReadFalse(userId);
	}

	public Notification markAsRead(Long id) {
		Optional<Notification> notification = notificationRepository.findById(id);
		if (notification.isPresent()) {
			Notification n = notification.get();
			n.setRead(true);
			return notificationRepository.save(n);
		}
		throw new RuntimeException("Notification non trouvée");
	}

	public void deleteNotification(Long id) {
		notificationRepository.deleteById(id);
	}

	public Optional<Notification> getNotificationById(Long id) {
		return notificationRepository.findById(id);
	}
}
