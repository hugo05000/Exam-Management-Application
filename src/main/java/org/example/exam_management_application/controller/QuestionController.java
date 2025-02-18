package org.example.exam_management_application.controller;

import org.example.exam_management_application.model.Question;
import org.example.exam_management_application.repository.QuestionRepository;
import org.example.exam_management_application.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getUserById(@PathVariable Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return new ResponseEntity<>(question.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()) {
            Question updatedQuestion = questionOptional.get();
            updatedQuestion.setQuestionTitle(question.getQuestionTitle());
            updatedQuestion.setCategory(question.getCategory());
            updatedQuestion.setDifficultyLevel(question.getDifficultyLevel());
            updatedQuestion.setOption_1(question.getOption_1());
            updatedQuestion.setOption_2(question.getOption_2());
            updatedQuestion.setOption_3(question.getOption_3());
            updatedQuestion.setOption_4(question.getOption_4());
            updatedQuestion.setRightAnswer(question.getRightAnswer());
            updatedQuestion.setQuizzes(question.getQuizzes());

            Question questionUpdated = questionRepository.save(updatedQuestion);
            return new ResponseEntity<>(questionUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()) {
            questionRepository.delete(questionOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
