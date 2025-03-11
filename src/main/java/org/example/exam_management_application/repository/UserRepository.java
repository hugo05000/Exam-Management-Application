package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface repository pour gérer les utilisateurs dans l'application.
 *
 * <p>Cette interface étend JpaRepository afin de fournir des opérations CRUD standards,
 * ainsi qu'une requête personnalisée permettant de filtrer les utilisateurs en fonction de leur rôle.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see User
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Récupère tous les utilisateurs ayant un rôle spécifié.
     *
     * @param role Rôle des utilisateurs recherchés (par exemple : ADMIN, TEACHER, STUDENT).
     * @return Liste d'utilisateurs correspondant au rôle spécifié.
     */
    @Query(value = "SELECT * FROM users WHERE role = :role", nativeQuery = true)
    List<User> findByRole(@Param("role") String role);
}
