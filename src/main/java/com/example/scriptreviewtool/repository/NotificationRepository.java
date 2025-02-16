package com.example.scriptreviewtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.scriptreviewtool.model.Notification;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientId(Long userId);
    List<Notification> findByRecipientIdAndReadFalse(Long userId);
}
