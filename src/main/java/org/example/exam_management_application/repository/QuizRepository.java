package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface repository pour la gestion des quiz dans l'application.
 *
 * <p>Cette interface étend JpaRepository afin de fournir des opérations CRUD standard ainsi qu'une
 * méthode personnalisée permettant de récupérer l'ensemble des quiz disponibles en base de données.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Quiz
 */
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    /**
     * Récupère tous les quiz existants via une requête SQL personnalisée.
     *
     * @return Liste complète de quiz disponibles.
     */
    @Query(value = "SELECT * FROM quiz", nativeQuery = true)
    List<Quiz> findAllQuiz();
}
