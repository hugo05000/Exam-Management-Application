package org.example.exam_management_application.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    // “TEACHER”, “STUDENT”, “ADMIN”
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "exam_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> exams = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "student_id"),         // fait référence à user_id
            inverseJoinColumns = @JoinColumn(name = "course_id")    // fait référence à course_id
    )
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<Exam> teacherExams = new ArrayList<>();

    //Todo getters et setters

}
