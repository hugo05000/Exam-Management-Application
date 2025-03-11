package org.example.exam_management_application.controller;

import org.example.exam_management_application.model.Exam;
import org.example.exam_management_application.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des examens dans l'application.
 *
 * <p>Ce contrôleur fournit une interface REST permettant aux enseignants de créer, consulter,
 * modifier et supprimer des examens. Le module vise à simplifier l'organisation des examens,
 * réduire les erreurs administratives et faciliter la communication avec les étudiants et l'administration.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Exam
 * @see ExamService
 */
@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    ExamService examService;

    /**
     * Récupère la liste complète de tous les examens disponibles.
     *
     * @return ResponseEntity avec une liste d'examens et statut HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return new ResponseEntity<>(examService.getAllExams(), HttpStatus.OK);
    }

    /**
     * Ajoute un nouvel examen dans la base de données.
     *
     * @param exam Examen à ajouter.
     * @return ResponseEntity avec l'examen créé et le statut HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam) {
        return new ResponseEntity<>(examService.createExam(exam), HttpStatus.CREATED);
    }

    /**
     * Récupère un examen spécifique par son identifiant unique.
     *
     * @param id Identifiant unique de l'examen.
     * @return ResponseEntity avec l'examen trouvé et statut HTTP OK, sinon NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Optional<Exam> exam = examService.getExamById(id);
        if (exam.isPresent()) {
            return new ResponseEntity<>(exam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Met à jour les détails d'un examen existant.
     *
     * @param id Identifiant de l'examen à modifier.
     * @param exam Données mises à jour pour l'examen.
     * @return ResponseEntity avec l'examen mis à jour et statut HTTP OK, sinon NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        Optional<Exam> updatedExam = examService.updateExam(id, exam);
        if (updatedExam.isPresent()) {
            return new ResponseEntity<>(updatedExam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Supprime un examen spécifié par son identifiant.
     *
     * @param id Identifiant de l'examen à supprimer.
     * @return ResponseEntity avec statut HTTP OK si la suppression est réussie, sinon NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        boolean deleted = examService.deleteExam(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}