package com.example.scriptreviewtool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scriptreviewtool.model.RevisionHistory;
import com.example.scriptreviewtool.model.Script;

@Repository
public interface RevisionHistoryRepository extends JpaRepository<RevisionHistory, Long> {
	List<RevisionHistory> findByScript(Script script);
}