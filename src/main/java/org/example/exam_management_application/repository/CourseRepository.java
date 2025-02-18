package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
