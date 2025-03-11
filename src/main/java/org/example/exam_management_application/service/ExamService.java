package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Exam;
import org.example.exam_management_application.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des examens dans l'application.
 *
 * <p>Ce service offre les opérations CRUD sur l'entité Exam en interagissant avec le repository associé.
 * Il permet de récupérer, créer, modifier et supprimer des examens, facilitant ainsi leur gestion administrative.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see Exam
 * @see ExamRepository
 */
@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    /**
     * Récupère la liste de tous les examens enregistrés.
     *
     * @return Liste de tous les examens existants.
     */
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

    /**
     * Récupère un examen spécifique par son identifiant.
     *
     * @param id Identifiant unique de l'examen.
     * @return Examen s'il existe, sinon un Optional vide.
     */
    public Optional<Exam> getExamById(Long id){
        return examRepository.findById(id);
    }

    /**
     * Crée un nouvel examen dans la base de données.
     *
     * @param exam L'examen à ajouter.
     * @return L'examen nouvellement créé.
     */
    public Exam createExam(Exam exam){
        return examRepository.save(exam);
    }

    /**
     * Met à jour un examen existant.
     *
     * @param id Identifiant de l'examen à modifier.
     * @param exam Données mises à jour de l'examen.
     * @return Examen mis à jour s'il existe, sinon un Optional vide.
     */
    public Optional<Exam> updateExam(Long id, Exam exam){
        Optional<Exam> examOptional = examRepository.findById(id);

        if (examOptional.isPresent()){
            Exam updatedExam = examOptional.get();
            updatedExam.setExamTitle(exam.getExamTitle());
            updatedExam.setStudents(exam.getStudents());
            updatedExam.setStudents(exam.getStudents());
            updatedExam.setTeacher(exam.getTeacher());

            Exam examUpdated = examRepository.save(updatedExam);
            return Optional.of(examUpdated);
        }
        return Optional.empty();
    }

    /**
     * Supprime un examen en fonction de son identifiant.
     *
     * @param id Identifiant de l'examen à supprimer.
     * @return true si l'examen a été supprimé avec succès, false s'il n'existe pas.
     */
    public boolean deleteExam(Long id){
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isPresent()){
            examRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
