package com.example.ouistrez.repository;

import com.example.ouistrez.entity.WellnessDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WellnessDocumentRepository extends JpaRepository<WellnessDocument, String> {
}
