package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Exam;
import org.springframework.data.repository.CrudRepository;

public interface ExamRepository extends CrudRepository<Exam, Long> {
}
