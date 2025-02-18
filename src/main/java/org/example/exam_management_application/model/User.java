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

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Exam> getTeacherExams() {
        return teacherExams;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setTeacherExams(List<Exam> teacherExams) {
        this.teacherExams = teacherExams;
    }
}
