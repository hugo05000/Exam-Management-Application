package org.example.exam_management_application.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une question dans l'application.
 *
 * <p>Cette classe définit une question utilisée dans des examens et quiz. Chaque question possède plusieurs choix possibles,
 * une réponse correcte, une catégorie ainsi qu'un niveau de difficulté. Elle est liée à un examen spécifique et peut également
 * être utilisée dans plusieurs quiz différents.</p>
 *
 * <p>Cette classe aide à structurer clairement les examens, simplifier leur gestion et améliorer l'organisation générale du processus d'évaluation.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Exam
 * @see Quiz
 */
@Entity
@Table(name = "questions")
public class Question {
    /** Identifiant unique généré automatiquement pour chaque question. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Catégorie à laquelle appartient la question (par exemple, Mathématiques, Géographie...). */
    private String category;

    /** Niveau de difficulté de la question (exemple : FACILE, MOYEN, DIFFICILE). */
    @Column(name = "difficulty_level")
    private String difficultyLevel;

    /** Première option de réponse proposée pour la question. */
    private String option_1;
    /** Deuxième option de réponse proposée pour la question. */
    private String option_2;
    /** Troisième option de réponse proposée pour la question. */
    private String option_3;
    /** Quatrième option de réponse proposée pour la question. */
    private String option_4;

    /** Libellé de la question. */
    @Column(name = "question_title")
    private String questionTitle;

    /** Réponse correcte à la question. */
    @Column(name = "right_answer")
    private String rightAnswer;

    /** Examen associé à cette question
     * @se Exam
     */
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    /** Liste des quiz contenant cette question.
     * @see Quiz
     */
    @ManyToMany(mappedBy = "questions")
    private List<Quiz> quizzes = new ArrayList<>();

    // Getters

    /** @return l'identifiant unique de la question. */
    public Long getId() {
        return id;
    }

    /** @return la catégorie de la question. */
    public String getCategory() {
        return category;
    }

    /** @return le niveau de difficulté de la question. */
    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    /** @return la première option de réponse proposée. */
    public String getOption_1() {
        return option_1;
    }

    /** @return la deuxième option de réponse proposée. */
    public String getOption_2() {
        return option_2;
    }

    /** @return la troisième option de réponse proposée. */
    public String getOption_3() {
        return option_3;
    }

    /** @return la quatrième option de réponse proposée. */
    public String getOption_4() {
        return option_4;
    }

    /** @return le libellé de la question. */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /** @return la réponse correcte à la question. */
    public String getRightAnswer() {
        return rightAnswer;
    }

    /**
     * @return la liste des quiz associés à cette question.
     * @see Quiz
     */
    public List<Quiz> getQuizzes() {
        return quizzes;
    }


    // Setters


    /**
     * Définit l'identifiant de la question.
     * @param id Identifiant de la question à définir.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Définit la catégorie de la question.
     * @param category Catégorie à définir (par exemple, Mathématiques, Géographie...).
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Définit la difficulté de la question.
     * @param difficultyLevel Difficulté à définir (exemple : FACILE, MOYEN, DIFFICILE).
     */
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Définit la première option de réponse à la question.
     * @param option_1 PRemière option de réponse à définir.
     */
    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }

    /**
     * Définit la deuxième option de réponse à la question.
     * @param option_2 PRemière option de réponse à définir.
     */
    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    /**
     * Définit la troisième option de réponse à la question.
     * @param option_3 PRemière option de réponse à définir.
     */
    public void setOption_3(String option_3) {
        this.option_3 = option_3;
    }

    /**
     * Définit la quatrième option de réponse à la question.
     * @param option_4 PRemière option de réponse à définir.
     */
    public void setOption_4(String option_4) {
        this.option_4 = option_4;
    }

    /**
     * Définit le libellé de la question.
     * @param questionTitle Libellé de la question à définir.
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    /**
     * Définit la bonne réponse à la question.
     * @param rightAnswer Réponse correcte à définir.
     */
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    /**
     * Définit la liste des quizs associés à cette question.
     * @param quizzes Liste des quizs associés à cette question à définir.
     * @see Quiz
     */
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
