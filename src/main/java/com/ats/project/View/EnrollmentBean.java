package com.ats.project.View;

import com.ats.project.model.*;
import com.ats.project.service.CoursesService;
import com.ats.project.service.EnrollmentService;
import com.ats.project.service.SemesterService;
import com.ats.project.service.StudentsService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Named("enrollmentBean")
@ViewScoped
public class EnrollmentBean implements Serializable {

    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private StudentsService studentService;
    @Autowired
    private CoursesService coursesService;
    @Autowired
    private SemesterService semesterService;

    private List<Enrollment> enrollmentList;
    private Enrollment selectedEnrollment;

    private List<Students> studentList;
    private List<Courses> coursesList;
    private List<Semester> semesterList;

    private Long selectedStudentId;
    private Long selectedCourseId;
    private Long selectedSemesterId;

    private Grades selectedGrade;
    private List<Grades> gradesList = Arrays.asList(Grades.values());

    @PostConstruct
    public void init() {
        enrollmentList = enrollmentService.findAll();
        studentList = studentService.findAll();
        coursesList = coursesService.findAll();
        semesterList = semesterService.findAll();
        resetForm();
    }

    public void loadEnrollmentsForStudent() {
        if (selectedStudentId == null) {
            // No student selected => show all enrollments
            enrollmentList = enrollmentService.findAll();
        } else {
            // Filter by student
            enrollmentList = enrollmentService.getEnrollmentsByStudentId(selectedStudentId);
        }
    }

    public void saveEnrollment() {
        Students student = studentList.stream()
                .filter(s -> s.getId().equals(selectedStudentId))
                .findFirst().orElse(null);

        Courses course = coursesList.stream()
                .filter(c -> c.getId().equals(selectedCourseId))
                .findFirst().orElse(null);

        Semester semester = semesterList.stream()
                .filter(s -> s.getId().equals(selectedSemesterId))
                .findFirst().orElse(null);

        selectedEnrollment.setStudent(student);
        selectedEnrollment.setCourses(course);
        selectedEnrollment.setSemester(semester);
        selectedEnrollment.setGrades(selectedGrade);

        if (selectedEnrollment.getEnrollmentDate() == null) {
            selectedEnrollment.setEnrollmentDate(LocalDate.now());
        }

        enrollmentService.saveEnrollment(selectedEnrollment);

        resetForm();
        enrollmentList = enrollmentService.findAll();
    }

    public void deleteEnrollment(Enrollment enrollment) {
        enrollmentService.deleteEnrollment(enrollment.getId());
        enrollmentList.remove(enrollment);
    }

    public void setSelectedEnrollment(Enrollment enrollment) {
        this.selectedEnrollment = enrollment;
        this.selectedStudentId = enrollment.getStudent().getId();
        this.selectedCourseId = enrollment.getCourses().getId();
        this.selectedSemesterId = enrollment.getSemester().getId();
        this.selectedGrade = enrollment.getGrades();
    }

    public void resetForm() {
        selectedEnrollment = new Enrollment();
        selectedStudentId = null;
        selectedCourseId = null;
        selectedSemesterId = null;
        selectedGrade = null;
    }

    // Getters & Setters
    public List<Enrollment> getEnrollmentList() { return enrollmentList; }
    public Enrollment getSelectedEnrollment() { return selectedEnrollment; }
    public List<Students> getStudentList() { return studentList; }
    public List<Courses> getCoursesList() { return coursesList; }
    public List<Semester> getSemesterList() { return semesterList; }
    public Long getSelectedStudentId() { return selectedStudentId; }
    public void setSelectedStudentId(Long selectedStudentId) { this.selectedStudentId = selectedStudentId; }
    public Long getSelectedCourseId() { return selectedCourseId; }
    public void setSelectedCourseId(Long selectedCourseId) { this.selectedCourseId = selectedCourseId; }
    public Long getSelectedSemesterId() { return selectedSemesterId; }
    public void setSelectedSemesterId(Long selectedSemesterId) { this.selectedSemesterId = selectedSemesterId; }
    public Grades getSelectedGrade() { return selectedGrade; }
    public void setSelectedGrade(Grades selectedGrade) { this.selectedGrade = selectedGrade; }
    public List<Grades> getGradesList() { return gradesList; }
}
