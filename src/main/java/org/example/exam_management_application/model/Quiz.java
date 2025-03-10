package org.example.exam_management_application.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un Quiz dans l'application web de gestion des examens développée pour la Faculté d'Économie et de Gestion (FEG).
 *
 * <p>Cette classe permet de regrouper plusieurs questions en un quiz structuré des évaluations pédagogiques. Chaque quiz peut contenir plusieurs questions, et chaque question peut appartenir à plusieurs quiz.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Question
 */
@Entity
@Table(name = "quizs")
public class Quiz {
    /** Identifiant unique généré automatiquement pour chaque quiz. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Titre descriptif du quiz. */
    @Column
    private String title;

    /**
     * Liste des questions contenues dans ce quiz.
     * Relation plusieurs-à-plusieurs gérée par une table intermédiaire quiz_questions.
     * @see Question
     */
    @ManyToMany
    @JoinTable(
            name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "questions_id")
    )
    private List<Question> questions = new ArrayList<>();

    // Getters
    /** @return identifiant du quiz */
    public Long getId() {
        return id;
    }

    /** @return le titre du quiz. */
    public String getTitle() {
        return title;
    }

    /** @return la liste des questions associées au quiz. */
    public List<Question> getQuestions() {
        return questions;
    }

    // Setters

    /**
     * Définit l'identifiant du quiz.
     * @param id Identifiant à définir
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Définit le titre du quiz.
     * @param title titre à définir
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Associe une liste de questions au quiz.
     * @param questions liste de questions
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
