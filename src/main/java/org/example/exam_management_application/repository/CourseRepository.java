package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface repository pour la gestion des cours dans l'application.
 *
 * <p>Cette interface étend JpaRepository pour fournir des opérations CRUD standards sur l'entité Course.
 * Elle permet d'ajouter, récupérer, modifier et supprimer des cours de manière simplifiée.
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Course
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
}
