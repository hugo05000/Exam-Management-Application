package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
