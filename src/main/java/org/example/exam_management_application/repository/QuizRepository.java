package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT * FROM quiz", nativeQuery = true)
    List<Quiz> findAllQuiz();
}
