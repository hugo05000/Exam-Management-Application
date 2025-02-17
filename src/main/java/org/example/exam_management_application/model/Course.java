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

    //Todo getters et setters

}
