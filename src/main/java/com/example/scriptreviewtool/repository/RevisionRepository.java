package com.example.scriptreviewtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Revision;
import com.example.scriptreviewtool.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {
    
    List<Revision> findByScriptIdOrderByCreatedAtDesc(Long scriptId);
    
    List<Revision> findByAuthorIdOrderByCreatedAtDesc(Long authorId);
    
    long countByAuthorAndCreatedAtBetween(User author, LocalDateTime startDate, LocalDateTime endDate);
}