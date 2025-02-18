package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
}
