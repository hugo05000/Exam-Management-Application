package org.example.exam_management_application.controller;

import org.example.exam_management_application.model.Course;
import org.example.exam_management_application.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST permettant la gestion des cours dans l'application.
 *
 * <p>Ce contrôleur fournit les points d'accès REST pour effectuer des opérations CRUD (Create, Read, Update, Delete)
 * sur les cours enregistrés dans la base de données. Il permet de simplifier la gestion pédagogique et d'améliorer
 * l'organisation des cours et inscriptions des étudiants.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Course
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    /**
     * Récupère la liste de tous les cours disponibles.
     *
     * @return ResponseEntity avec une liste des cours et le statut HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    /**
     * Ajoute un nouveau cours à la base de données.
     *
     * @param course Le cours à ajouter.
     * @return ResponseEntity avec le cours créé et le statut HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
    }

    /**
     * Récupère un cours spécifique à partir de son identifiant.
     *
     * @param id Identifiant unique du cours.
     * @return ResponseEntity avec le cours recherché et le statut HTTP OK si trouvé, sinon NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Met à jour les informations d'un cours existant.
     *
     * @param id Identifiant du cours à mettre à jour
     * @param course Données actualisées du cours
     * @return ResponseEntity avec le cours mis à jour et le statut HTTP OK, ou NOT_FOUND si inexistant.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> updatedCourse = courseService.updateCourse(id, course);
        if (updatedCourse.isPresent()) {
            return new ResponseEntity<>(updatedCourse.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Supprime un cours identifié par son ID.
     *
     * @param id Identifiant du cours à supprimer.
     * @return ResponseEntity avec le statut HTTP OK si la suppression réussit, ou NOT_FOUND sinon.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
