package org.example.exam_management_application.controller;

import org.example.exam_management_application.model.Question;
import org.example.exam_management_application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des questions dans l'application.
 *
 * <p>Ce contrôleur permet aux utilisateurs de créer, récupérer, modifier et supprimer des questions.
 * Les questions sont essentielles pour constituer les examens et quiz, facilitant ainsi la gestion et l'organisation
 * pédagogique tout en réduisant les erreurs humaines dans le processus de gestion.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Question
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /**
     * Récupère toutes les questions enregistrées dans l'application.
     *
     * @return ResponseEntity contenant une liste de questions et le statut HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    /**
     * Ajoute une nouvelle question dans la base de données.
     *
     * @param question La question à créer.
     * @return ResponseEntity contenant la question créée et statut HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.createQuestion(question), HttpStatus.CREATED);
    }

    /**
     * Récupère une question spécifique par son identifiant.
     *
     * @param id Identifiant unique de la question.
     * @return ResponseEntity contenant la question demandée et statut HTTP OK, ou NOT_FOUND si inexistante.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Question> getUserById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        if (question.isPresent()) {
            return new ResponseEntity<>(question.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Met à jour une question existante par son identifiant.
     *
     * @param id Identifiant de la question à mettre à jour.
     * @param question Données mises à jour de la question.
     * @return ResponseEntity contenant la question mise à jour et statut HTTP OK, ou NOT_FOUND si inexistante.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Optional<Question> updatedQuestion = questionService.updateQuestion(id, question);
        if (updatedQuestion.isPresent()) {
            return new ResponseEntity<>(updatedQuestion.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Supprime une question en spécifiant son identifiant.
     *
     * @param id Identifiant de la question à supprimer.
     * @return ResponseEntity avec statut HTTP OK si suppression réussie, ou NOT_FOUND sinon.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        boolean deleted = questionService.deleteQuestion(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
