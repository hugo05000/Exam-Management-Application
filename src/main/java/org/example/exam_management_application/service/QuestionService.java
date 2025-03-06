package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Question;
import org.example.exam_management_application.model.User;
import org.example.exam_management_application.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    // Récupère toutes les questions
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    // Récupère un cours par son id
    public Optional<Question> getQuestionById(Long id){
        return questionRepository.findById(id);
    }

    // Créer une nouvelle question
    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    // Met à jour une question existante
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

    // Supprime une question par son identifiant
    public boolean deleteQuestion(Long id){
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()){
            questionRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
