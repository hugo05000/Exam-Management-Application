package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
