package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Course;
import org.example.exam_management_application.model.Quiz;
import org.example.exam_management_application.model.User;
import org.example.exam_management_application.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    // Récupère tous les quizs
    public List<Quiz> getAllQuiz(){
        return quizRepository.findAll();
    }

    // Crée un nouveau quiz
    public Quiz createQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    // Récupère un quiz par son identifiant
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    // Met à jour un quiz existant
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

    // Supprime un quiz par son identifiant
    public boolean deleteQuiz(Long id){
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()){
            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
