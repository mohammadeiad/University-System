package com.ats.project.View;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

import com.ats.project.model.Courses;
import com.ats.project.model.Faculty;
import com.ats.project.service.CoursesService;
import com.ats.project.service.FacultyService;

import org.springframework.beans.factory.annotation.Autowired;

@Named("coursesBean")
@ViewScoped
public class CoursesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Courses selectedCourse;
    private Long selectedFacultyId;

    private List<Courses> coursesList;
    private List<Faculty> facultyList;

    @Autowired
    private CoursesService courseService;

    @Autowired
    private FacultyService facultyService;

    @PostConstruct
    public void init() {
        selectedCourse = new Courses();
        refreshCourses();
        refreshFaculties();
    }

    // =========================
    // Create Course
    // =========================
    public void createCourse() {
        try {
            if (selectedCourse != null && selectedFacultyId != null) {
                setRelations();
                courseService.createCourses(selectedCourse);
                refreshCourses();
                resetForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // Update Course
    // =========================
    public void updateCourse() {
        try {
            if (selectedCourse != null && selectedCourse.getId() != null) {
                setRelations();
                courseService.updateCourse(selectedCourse.getId(),selectedCourse);
                refreshCourses();
                resetForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // Save Course (Create or Update)
    // =========================
    public void saveCourse() {
        if (selectedCourse == null || selectedCourse.getId() == null) {
            createCourse();
        } else {
            updateCourse();
        }
    }

    // =========================
    // Delete Course
    // =========================
    public void deleteCourse(Courses course) {
        try {
            if (course != null && course.getId() != null) {
                courseService.deleteCourse(course.getId());
                refreshCourses();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // Set relations
    // =========================
    private void setRelations() {
        if (selectedFacultyId != null) {
            selectedCourse.setFaculty(facultyService.findById(selectedFacultyId));
        }
    }

    // =========================
    // Refresh lists
    // =========================
    private void refreshCourses() {
        coursesList = courseService.findAll();
    }

    private void refreshFaculties() {
        facultyList = facultyService.findAll();
    }

    // =========================
    // Reset form
    // =========================
    public void resetForm() {
        selectedCourse = new Courses();
        selectedFacultyId = null;
    }

    // =========================
    // Getters & Setters
    // =========================
    public Courses getSelectedCourse() { return selectedCourse; }

    public void setSelectedCourse(Courses course) {
        this.selectedCourse = course;
        if (course != null && course.getFaculty() != null) {
            this.selectedFacultyId = course.getFaculty().getId();
        } else {
            this.selectedFacultyId = null;
        }
    }

    public Long getSelectedFacultyId() { return selectedFacultyId; }
    public void setSelectedFacultyId(Long selectedFacultyId) { this.selectedFacultyId = selectedFacultyId; }

    public List<Courses> getCoursesList() { return coursesList; }
    public void setCoursesList(List<Courses> coursesList) { this.coursesList = coursesList; }

    public List<Faculty> getFacultyList() { return facultyList; }
    public void setFacultyList(List<Faculty> facultyList) { this.facultyList = facultyList; }
}
