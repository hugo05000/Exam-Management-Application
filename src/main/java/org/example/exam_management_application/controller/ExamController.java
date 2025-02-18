package org.example.exam_management_application.controller;

import org.example.exam_management_application.model.Exam;
import org.example.exam_management_application.repository.ExamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamRepository examRepository;

    public ExamController(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return new ResponseEntity<>(examRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam) {
        return new ResponseEntity<>(examRepository.save(exam), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            return new ResponseEntity<>(exam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        Optional<Exam> examOptional = examRepository.findById(id);

        if (examOptional.isPresent()) {
            Exam updatedExam = examOptional.get();
            updatedExam.setExamTitle(exam.getExamTitle());
            updatedExam.setCourse(exam.getCourse());
            updatedExam.setTeacher(exam.getTeacher());
            updatedExam.setStudents(exam.getStudents());

            Exam examUpdated = examRepository.save(updatedExam);
            return new ResponseEntity<>(examUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        Optional<Exam> examOptional = examRepository.findById(id);

        if (examOptional.isPresent()) {
            examRepository.delete(examOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
