package com.ats.project.View;

import com.ats.project.model.*;
import com.ats.project.service.*;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named("studentsBean")
@ViewScoped
public class StudentsBean implements Serializable {

    private Students selectedStudent = new Students();
    private Long selectedFacultyId;
    private Long selectedMajorId;
    private Long selectedNationalityId;
    private Long selectedCourseId;

    private List<Students> studentsList;
    private List<Faculty> facultyList;
    private List<Major> majorList;
    private List<Nationality> nationalityList;

    @Inject
    private StudentsService studentsService;
    @Inject
    private FacultyService facultyService;
    @Inject
    private MajorService majorService;
    @Inject
    private NationalityService nationalityService;
    @Inject
    private CoursesService coursesService;

    // =========================
    // Create Student
    // =========================
    public void createStudent() {
        try {
            // Set relations
            setRelations();

            studentsService.createStudent(selectedStudent);
            refreshStudents();
            resetForm();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // Update Student
    // =========================
    public void updateStudent() {
        try {
            if (selectedStudent != null && selectedStudent.getId() != null) {
                setRelations();
                studentsService.updateStudent(selectedStudent.getId(), selectedStudent);
                refreshStudents();
                resetForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // Save (Create or Update)
    // =========================
    public void saveStudent() {
        if (selectedStudent == null || selectedStudent.getId() == null) {
            createStudent();
        } else {
            updateStudent();
        }
    }

    // =========================
    // Delete Student
    // =========================
    public void deleteStudent(Students student) throws Exception {
        if (student != null && student.getId() != null) {
            studentsService.deleteStudent(student.getId());
            refreshStudents();
        }
    }

    // =========================
    // Helper: set relations
    // =========================
    private void setRelations() {
        if (selectedFacultyId != null)
            selectedStudent.setFaculty(facultyService.findById(selectedFacultyId));

        if (selectedMajorId != null)
            selectedStudent.setMajor(majorService.findById(selectedMajorId));

        if (selectedNationalityId != null)
            selectedStudent.setNationality(nationalityService.findById(selectedNationalityId));

        if (selectedCourseId != null)
            selectedStudent.setCourses(Collections.singletonList(coursesService.findById(selectedCourseId)));
    }

    // =========================
    // Helper: refresh students list
    // =========================
    private void refreshStudents() {
        studentsList = studentsService.findAll();
    }

    // =========================
    // Reset form
    // =========================
    public void resetForm() {
        selectedStudent = new Students();
        selectedFacultyId = null;
        selectedMajorId = null;
        selectedNationalityId = null;
        selectedCourseId = null;
    }

    // =========================
    // Getters
    // =========================
    public Students getSelectedStudent() { return selectedStudent; }
    public void setSelectedStudent(Students selectedStudent) { this.selectedStudent = selectedStudent; }

    public Long getSelectedFacultyId() { return selectedFacultyId; }
    public void setSelectedFacultyId(Long selectedFacultyId) { this.selectedFacultyId = selectedFacultyId; }

    public Long getSelectedMajorId() { return selectedMajorId; }
    public void setSelectedMajorId(Long selectedMajorId) { this.selectedMajorId = selectedMajorId; }

    public Long getSelectedNationalityId() { return selectedNationalityId; }
    public void setSelectedNationalityId(Long selectedNationalityId) { this.selectedNationalityId = selectedNationalityId; }

    public Long getSelectedCourseId() { return selectedCourseId; }
    public void setSelectedCourseId(Long selectedCourseId) { this.selectedCourseId = selectedCourseId; }

    public List<Students> getStudentsList() {
        if (studentsList == null) refreshStudents();
        return studentsList;
    }
    public void setStudentsList(List<Students> studentsList) { this.studentsList = studentsList; }

    public List<Faculty> getFacultyList() {
        if (facultyList == null) facultyList = facultyService.findAll();
        return facultyList;
    }

    public List<Major> getMajorList() {
        if (majorList == null) majorList = majorService.findAll();
        return majorList;
    }

    public List<Nationality> getNationalityList() {
        if (nationalityList == null) nationalityList = nationalityService.findAll();
        return nationalityList;
    }
}
