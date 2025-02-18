package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(value = """
        SELECT e.* 
        FROM exam e
        JOIN users u ON e.teacher_id = u.user_id
        WHERE u.username = :username
        ORDER BY e.exam_id ASC
        LIMIT 1
        """, nativeQuery = true)
    Exam findFirstExamByTeacher(@Param("username") String username);
}
