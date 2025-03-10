package org.example.exam_management_application.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un utilisateur dans l'application.
 * Elle contient les informations essentielles relatives aux utilisateurs (étudiants et enseignants),
 * et permet de gérer les rôles, les informations personnelles et les relations avec les examens et les cours.
 *
 * <p>Cette classe fait partie d'une application web conçue pour simplifier la gestion des examens,
 * des étudiants et des enseignants pour la Faculté d'Économie et de Gestion (FEG).
 * L'objectif est d'éliminer les redondances et réduire les erreurs humaines en automatisant
 * la gestion des détails relatifs aux examens.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Exam
 * @see Course
 */
@Entity
@Table(name = "users")
public class User {
    /** Identifiant unique généré automatiquement pour chaque utilisateur. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /** Indique si l'utilisateur est actif ou non. */
    private boolean active;

    /** Prénom de l'utilisateur. */
    @Column(name = "first_name")
    private String firstName;

    /** Nom de famille de l'utilisateur. */
    @Column(name = "last_name")
    private String lastName;

    /** Adresse email de l'utilisateur. */
    private String email;

    /** Mot de passe de l'utilisateur. */
    private String password;

    /** Rôle attribué à l'utilisateur (exemple : étudiant, enseignant, administrateur). */
    private String role;

    /** Nom d'utilisateur pour se connecter à l'application. */
    private String username;

    /** Liste des examens auxquels l'utilisateur (étudiant) est inscrit. */
    @ManyToMany
    @JoinTable(
            name = "exam_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> exams = new ArrayList<>();

    /** Liste des cours auxquels l'utilisateur (étudiant) est inscrit. */
    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    /** Liste des examens créés par l'utilisateur s'il est enseignant. */
    @OneToMany(mappedBy = "teacher")
    private List<Exam> teacherExams = new ArrayList<>();

    // Getters

    /** @return Identifiant de l'utilisateur. */
    public Long getId() {
        return id;
    }

    /** @return vrai si l'utilisateur est actif, faux sinon. */
    public boolean isActive() {
        return active;
    }

    /** @return le nom de famille de l'utilisateur. */
    public String getFirstName() {
        return firstName;
    }

    /** @return le nom de famille de l'utilisateur. */
    public String getLastName() {
        return lastName;
    }

    /** @return l'adresse email de l'utilisateur. */
    public String getEmail() {
        return email;
    }

    /** @return le mot de passe de l'utilisateur. */
    public String getPassword() {
        return password;
    }

    /** @return le rôle de l'utilisateur (par exemple, ADMIN, TEACHER ou STUDENT). */
    public String getRole() {
        return role;
    }

    /** @return le nom d'utilisateur pour l'authentification. */
    public String getUsername() {
        return username;
    }

    /** @return la liste des examens auxquels l'utilisateur est inscrit en tant qu'étudiant.
     * @see Exam
     */
    public List<Exam> getExams() {
        return exams;
    }

    /**
     * @return les cours auxquels l'utilisateur est inscrit en tant qu'étudiant.
     * @see Course
     */
    public List<Course> getCourses() {
        return courses;
    }

    /** @return la liste des examens créés par cet utilisateur en tant qu'enseignant.
     * @see Exam
     */
    public List<Exam> getTeacherExams() {
        return teacherExams;
    }

    // Setters

    /**
     * Définit l'identifiant de l'utilisateur
     * @param id Identifiant à définir
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Définit l'état de l'utilisateur (actif ou non)
     * @param active Actif ou non (True ou False)
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Définit le prénom de l'utilisateur
     * @param firstName prénom à définir
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Définit le nom de l'utilisateur
     * @param lastName nom à définir
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Définit l'email de l'utilisateur
     * @param email email à définir
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Définit le mot de passe de l'utilisateur
     * @param password mot de passe à définir
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Définit le rôle de l'utilisateur
     * @param role Rôle de l'utilisateur (par exemple, ADMIN, TEACHER ou STUDENT)
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Définit le nom d'utilisateur de l'utilisateur
     * @param username Nom d'utilisateur de l'utilisateur
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Définit la liste des examens auxquels l'utilisateur est inscrit en tant qu'étudiant.
     * @param exams Liste des examens auxquels l'utilisateur est inscrit.
     * @see Exam
     */
    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    /**
     * Définit la liste des cours auxquels l'utilisateur est inscrit en tant qu'étudiant.
     * @param courses Liste des cours auxquels l'utilisateur est inscrit.
     * @see Course
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /** Définit la liste des examens créés par cet utilisateur en tant qu'enseignant.
     * @param teacherExams Liste des examens créés par l'utilisateur
     * @see Exam
     */
    public void setTeacherExams(List<Exam> teacherExams) {
        this.teacherExams = teacherExams;
    }
}
