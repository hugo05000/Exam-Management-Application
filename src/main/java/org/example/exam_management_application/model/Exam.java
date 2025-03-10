package org.example.exam_management_application.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un examen dans l'application.
 *
 * <p>Cette classe définit un examen avec son titre, son enseignant responsable, les questions associées,
 * ainsi que les étudiants inscrits à cet examen. Elle permet aux enseignants de spécifier clairement les caractéristiques
 * des examens, facilitant ainsi la gestion administrative et réduisant les erreurs humaines liées à la gestion manuelle.
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see User
 * @see Question
 */
@Entity
@Table(name = "exams")
public class Exam {
    /** Identifiant unique généré automatiquement pour chaque examen. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Titre de l'examen. */
    @Column(name = "exam_title")
    private String examTitle;

    /**
     * Enseignant responsable de l'examen.
     * @see User
     */
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    /** Liste des questions associées à cet examen.
     * @see Question
     */
    @OneToMany(mappedBy = "exam")
    private List<Question> questions = new ArrayList<>();

    /** Liste des étudiants inscrits à cet examen.
     * @see User
     */
    @ManyToMany(mappedBy = "exams")
    private List<User> students = new ArrayList<>();

    // Getters

    /** @return l'identifiant unique de l'examen. */
    public Long getId() {
        return id;
    }

    /** @return le titre de l'examen. */
    public String getExamTitle() {
        return examTitle;
    }

    /**
     * @return l'enseignant responsable de cet examen.
     * @see User
     */
    public User getTeacher() {
        return teacher;
    }

    /**
     * @return la liste des étudiants inscrits à l'examen.
     * @see User
     */
    public List<User> getStudents() {
        return students;
    }

    // Setters

    /**
     * Modifie l'identifiant unique de l'examen.
     * @param id Identifiant de l'examen à définir.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Définit le titre de l'examen.
     * @param examTitle Titre de l'examen à définir.
     */
    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    /**
     * Définit l'enseignant responsable de l'examen.
     * @param teacher Enseignant responsable de l'examen à définir.
     * @see User
     */
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    /**
     * Définit la liste des étudiants inscrits à cet examen.
     * @param students Liste des étudiants à inscrire à l'examen.
     * @see User
     */
    public void setStudents(List<User> students) {
        this.students = students;
    }
}
