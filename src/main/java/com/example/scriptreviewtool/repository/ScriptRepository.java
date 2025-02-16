package com.example.scriptreviewtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.Script;
import com.example.scriptreviewtool.model.Status;

import java.util.List;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {
    
    List<Script> findByStatus(Status status);
    
    List<Script> findByReviewersId(Long reviewerId);
    
    List<Script> findByAuthorId(Long authorId);
    
    long countByStatus(Status status);
    
    @Query(value = "SELECT AVG(TIMESTAMPDIFF(SECOND, s.created_at, " +
           "(SELECT MAX(r.created_at) FROM revision r WHERE r.script_id = s.id))) " +
           "FROM script s WHERE s.status = 'APPROVED'", nativeQuery = true)
    Double calculateAverageReviewTime();
    
    @Query("SELECT s FROM Script s WHERE s.status = :status " +
           "ORDER BY s.createdAt DESC")
    List<Script> findRecentScriptsByStatus(Status status);
    
    @Query("SELECT COUNT(DISTINCT s) FROM Script s " +
           "JOIN s.reviewers r WHERE r.id = :reviewerId " +
           "AND s.status = :status")
    long countScriptsByReviewerAndStatus(Long reviewerId, Status status);
}