package org.example.exam_management_application.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "course")
    private List<Exam> exams = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<User> students = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
