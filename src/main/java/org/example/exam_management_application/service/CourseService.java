package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Course;
import org.example.exam_management_application.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des cours dans l'application.
 *
 * <p>Ce service offre les opérations CRUD sur l'entité Course en interagissant avec le repository associé.
 * Il permet de récupérer, créer, modifier et supprimer des cours, facilitant ainsi leur gestion administrative.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Course
 * @see CourseRepository
 */
@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    /**
     * Récupère la liste de tous les cours enregistrés.
     *
     * @return Liste de tous les cours existants.
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Crée un nouveau cours dans la base de données.
     *
     * @param course Le cours à ajouter.
     * @return Le cours nouvellement créé.
     */
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Récupère un cours spécifique par son identifiant.
     *
     * @param id Identifiant unique du cours.
     * @return Cours s'il existe, sinon un Optional vide.
     */
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    /**
     * Met à jour un cours existant.
     *
     * @param id Identifiant du cours à modifier.
     * @param course Données mises à jour du cours.
     * @return Cours mis à jour s'il existe, sinon un Optional vide.
     */
    public Optional<Course> updateCourse(Long id, Course course) {
        return courseRepository.findById(id).map(existingCourse -> {
            existingCourse.setTitle(course.getTitle());
            existingCourse.setStudents(course.getStudents());
            return courseRepository.save(existingCourse);
        });
    }

    /**
     * Supprime un cours en fonction de son identifiant.
     *
     * @param id Identifiant du cours à supprimer.
     * @return true si le cours a été supprimé avec succès, false s'il n'existe pas.
     */
    public boolean deleteCourse(Long id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }
}