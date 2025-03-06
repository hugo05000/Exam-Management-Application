package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Exam;
import org.example.exam_management_application.model.Quiz;
import org.example.exam_management_application.model.User;
import org.example.exam_management_application.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    // Récupère tous les exams
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

    // Récupère un exam par son id
    public Optional<Exam> getExamById(Long id){
        return examRepository.findById(id);
    }

    // Crée un nouvel exam
    public Exam createExam(Exam exam){
        return examRepository.save(exam);
    }

    // Met à jour un exam existant
    public Optional<Exam> updateExam(Long id, Exam exam){
        Optional<Exam> examOptional = examRepository.findById(id);

        if (examOptional.isPresent()){
            Exam updatedExam = examOptional.get();
            updatedExam.setExamTitle(exam.getExamTitle());
            updatedExam.setStudents(exam.getStudents());
            updatedExam.setStudents(exam.getStudents());


            Exam examUpdated = examRepository.save(updatedExam);
            return Optional.of(examUpdated);
        }
        return Optional.empty();
    }

    // Supprime un exam par son identifiant
    public boolean deleteExam(Long id){
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isPresent()){
            examRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
