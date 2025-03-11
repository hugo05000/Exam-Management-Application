package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interface repository pour gérer l'entité Exam dans l'application.
 *
 * <p>Cette interface étend JpaRepository afin de fournir des méthodes CRUD standards,
 * ainsi que des méthodes personnalisées permettant d'effectuer des requêtes spécifiques sur la table des examens.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Exam
 */
public interface ExamRepository extends JpaRepository<Exam, Long> {
    /**
     * Recherche le premier examen associé à un enseignant spécifié par son nom d'utilisateur.
     *
     * @param username Nom d'utilisateur de l'enseignant recherché.
     * @return Le premier examen trouvé pour l'enseignant spécifié.
     */
    @Query(value = """
        SELECT e.* 
        FROM exam e
        JOIN users u ON e.teacher_id = u.user_id
        WHERE u.username = :username
        ORDER BY e.exam_id ASC
        LIMIT 1
        """, nativeQuery = true)
    Exam findFirstExamByTeacher(@Param("username") String username);
}
