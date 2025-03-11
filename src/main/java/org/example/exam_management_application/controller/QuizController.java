package org.example.exam_management_application.controller;

import org.example.exam_management_application.model.Quiz;
import org.example.exam_management_application.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST permettant de gérer les quiz dans l'application.
 *
 * <p>Ce contrôleur expose des endpoints permettant d'effectuer les opérations CRUD
 * sur les quiz. Il vise à simplifier la création, modification et gestion des quiz,
 * contribuant ainsi à l'amélioration de l'organisation pédagogique de la faculté.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Quiz
 */
@RestController
@RequestMapping("/api/quizs")
public class QuizController {

    @Autowired
    QuizService quizService;

    /**
     * Récupère l'ensemble des quiz disponibles.
     *
     * @return ResponseEntity avec la liste de tous les quiz et statut HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuiz(), HttpStatus.OK);
    }

    /**
     * Crée un nouveau quiz dans l'application.
     *
     * @param quiz Objet quiz à créer.
     * @return ResponseEntity contenant le quiz créé et statut HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return new ResponseEntity<>(quizService.createQuiz(quiz), HttpStatus.CREATED);
    }

    /**
     * Récupère un quiz spécifique en fonction de son identifiant unique.
     *
     * @param id Identifiant unique du quiz.
     * @return ResponseEntity contenant le quiz recherché et statut HTTP OK, sinon NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isPresent()) {
            return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Met à jour un quiz existant.
     *
     * @param id Identifiant du quiz à modifier.
     * @param quiz Données mises à jour du quiz.
     * @return ResponseEntity avec le quiz mis à jour et statut HTTP OK, sinon NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        Optional<Quiz> updatedQuiz = quizService.updateQuiz(id, quiz);
        if (updatedQuiz.isPresent()) {
            return new ResponseEntity<>(updatedQuiz.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Supprime un quiz existant identifié par son ID.
     *
     * @param id Identifiant du quiz à supprimer.
     * @return ResponseEntity avec statut HTTP OK si suppression réussie, sinon NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        boolean deleted = quizService.deleteQuiz(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
