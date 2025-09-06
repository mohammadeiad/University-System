package com.ats.project.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Students student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Courses courses;
    @ManyToOne(fetch = FetchType.EAGER)
    private  Semester semester;
    private LocalDate enrollmentDate;
    @Enumerated(EnumType.STRING)
    private Grades grades;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrades(Grades grades) {
        this.grades = grades;
    }


    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", student=" + student +
                ", courses=" + courses +
                ", semester=" + semester +
                ", enrollmentDate=" + enrollmentDate +
                ", grades=" + grades +
                '}';
    }
}
