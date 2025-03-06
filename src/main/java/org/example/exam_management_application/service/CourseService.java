package org.example.exam_management_application.service;

import org.example.exam_management_application.model.Course;
import org.example.exam_management_application.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    // Récupère tous les cours
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Crée un nouveau cours
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Récupère un cours par son identifiant
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Met à jour un cours existant
    public Optional<Course> updateCourse(Long id, Course course) {
        return courseRepository.findById(id).map(existingCourse -> {
            existingCourse.setTitle(course.getTitle());
            existingCourse.setStudents(course.getStudents());
            return courseRepository.save(existingCourse);
        });
    }

    // Supprime un cours par son identifiant
    public boolean deleteCourse(Long id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }
}