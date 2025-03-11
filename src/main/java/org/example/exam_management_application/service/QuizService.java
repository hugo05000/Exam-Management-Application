package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Quiz;
import org.example.exam_management_application.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des quiz dans l'application.
 *
 * <p>Ce service offre les opérations CRUD sur l'entité Quiz en interagissant avec le repository associé.
 * Il permet de récupérer, créer, modifier et supprimer des quiz, facilitant ainsi leur gestion
 * pour les examens et évaluations.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Quiz
 * @see QuizRepository
 */
@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    /**
     * Récupère la liste de tous les quiz enregistrés.
     *
     * @return Liste de tous les quiz existants.
     */
    public List<Quiz> getAllQuiz(){
        return quizRepository.findAll();
    }

    /**
     * Crée un nouveau quiz dans la base de données.
     *
     * @param quiz Le quiz à ajouter.
     * @return Le quiz nouvellement créé.
     */
    public Quiz createQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    /**
     * Récupère un quiz spécifique par son identifiant.
     *
     * @param id Identifiant unique du quiz.
     * @return Quiz s'il existe, sinon un Optional vide.
     */
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    /**
     * Met à jour un quiz existant.
     *
     * @param id Identifiant du quiz à modifier.
     * @param quiz Données mises à jour du quiz.
     * @return Quiz mis à jour s'il existe, sinon un Optional vide.
     */
    public Optional<Quiz> updateQuiz(Long id, Quiz quiz){
        Optional<Quiz> quizOptional = quizRepository.findById(id);

        if (quizOptional.isPresent()){
            Quiz updatedQuiz = quizOptional.get();
            updatedQuiz.setQuestions(quiz.getQuestions());
            updatedQuiz.setTitle(quiz.getTitle());

            Quiz quizUpdated = quizRepository.save(updatedQuiz);
            return Optional.of(quizUpdated);
        }
        return Optional.empty();
    }

    /**
     * Supprime un quiz en fonction de son identifiant.
     *
     * @param id Identifiant du quiz à supprimer.
     * @return true si le quiz a été supprimé avec succès, false s'il n'existe pas.
     */
    public boolean deleteQuiz(Long id){
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()){
            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
