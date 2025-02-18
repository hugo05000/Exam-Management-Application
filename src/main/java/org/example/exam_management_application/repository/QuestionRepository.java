package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
