package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Question;
import org.example.exam_management_application.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des questions dans l'application.
 *
 * <p>Ce service offre les opérations CRUD sur l'entité Question en interagissant avec le repository associé.
 * Il permet de récupérer, créer, modifier et supprimer des questions, facilitant ainsi leur gestion
 * pour les quiz et examens.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Question
 * @see QuestionRepository
 */
@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    /**
     * Récupère la liste de toutes les questions enregistrées.
     *
     * @return Liste de toutes les questions existantes.
     */
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    /**
     * Récupère une question spécifique par son identifiant.
     *
     * @param id Identifiant unique de la question.
     * @return Question si elle existe, sinon un Optional vide.
     */
    public Optional<Question> getQuestionById(Long id){
        return questionRepository.findById(id);
    }

    /**
     * Crée une nouvelle question dans la base de données.
     *
     * @param question La question à ajouter.
     * @return La question nouvellement créée.
     */
    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    /**
     * Met à jour une question existante.
     *
     * @param id Identifiant de la question à modifier.
     * @param question Données mises à jour de la question.
     * @return Question mise à jour si elle existe, sinon un Optional vide.
     */
    public Optional<Question> updateQuestion(Long id, Question question){
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()){
            Question updatedQuestion = questionOptional.get();

            updatedQuestion.setQuestionTitle(question.getQuestionTitle());
            updatedQuestion.setQuizzes(question.getQuizzes());
            updatedQuestion.setCategory(question.getCategory());
            updatedQuestion.setRightAnswer(question.getRightAnswer());
            updatedQuestion.setOption_1(question.getOption_1());
            updatedQuestion.setOption_2(question.getOption_2());
            updatedQuestion.setOption_3(question.getOption_3());
            updatedQuestion.setOption_4(question.getOption_4());
            updatedQuestion.setDifficultyLevel(question.getDifficultyLevel());

            Question questionUpdated = questionRepository.save(updatedQuestion);
            return Optional.of(updatedQuestion);
        }
        return Optional.empty();
    }

    /**
     * Supprime une question en fonction de son identifiant.
     *
     * @param id Identifiant de la question à supprimer.
     * @return true si la question a été supprimée avec succès, false si elle n'existe pas.
     */
    public boolean deleteQuestion(Long id){
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()){
            questionRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
