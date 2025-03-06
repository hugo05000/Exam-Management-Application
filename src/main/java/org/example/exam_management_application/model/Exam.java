package org.example.exam_management_application.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_title")
    private String examTitle;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "exam")
    private List<Question> questions = new ArrayList<>();

    @ManyToMany(mappedBy = "exams")
    private List<User> students = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public User getTeacher() {
        return teacher;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
