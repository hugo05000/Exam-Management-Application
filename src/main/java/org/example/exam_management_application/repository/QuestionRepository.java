package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface repository pour la gestion des questions dans l'application.
 *
 * <p>Cette interface étend JpaRepository pour fournir des opérations CRUD standards sur l'entité Question.
 * Elle permet d'ajouter, récupérer, modifier et supprimer des questions de manière simplifiée.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Question
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
