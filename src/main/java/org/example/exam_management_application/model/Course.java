package org.example.exam_management_application.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un cours dans l'application.
 *
 * <p>Cette classe permet de gérer les informations d'un cours, notamment son titre et les étudiants inscrits.
 * Elle facilite l'organisation pédagogique en simplifiant la gestion des inscriptions des étudiants aux cours proposés par la faculté.</p>
 *
 * <p>L'objectif général est d'améliorer la gestion administrative, réduire les redondances et minimiser les erreurs humaines liées à la gestion manuelle des cours et des inscriptions.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see User
 */
@Entity
@Table(name = "course")
public class Course {
    /** Identifiant unique généré automatiquement pour chaque cours. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Titre descriptif du cours. */
    @Column
    private String title;

    /**
     * Liste des étudiants inscrits à ce cours.
     * Relation ManyToMany inversée par rapport à l'entité User.
     */
    @ManyToMany(mappedBy = "courses")
    private List<User> students = new ArrayList<>();

    // Getters

    /** @return identifiant du cours */
    public Long getId() {
        return id;
    }

    /** @return le titre du cours. */
    public String getTitle() {
        return title;
    }

    /**
     * @return la liste des étudiants inscrits à ce cours.
     * @see User
     */
    public List<User> getStudents() {
        return students;
    }

    // Setters

    /**
     * Définit l'identifiant unique du cours.
     * @param id Identifiant à définir.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Définit le titre du cours.
     * @param title Titre du cours à définir
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Associe une liste d'étudiants au cours.
     * @param students d'étudiants participant au cours à définir.
     * @see User
     */
    public void setStudents(List<User> students) {
        this.students = students;
    }
}
