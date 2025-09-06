package com.ats.project.View;

import com.ats.project.model.Faculty;
import com.ats.project.service.FacultyService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FacultyBean implements Serializable {

    private final FacultyService facultyService;
    private List<Faculty> facultyList;
    private Faculty selectedFaculty;

    @Inject
    public FacultyBean(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostConstruct
    public void init() {
        refreshFaculties();
        resetForm();
    }

    public void saveFaculty() {
        facultyService.saveFaculty(selectedFaculty);
        refreshFaculties();
        resetForm();
    }

    public void editFaculty(Faculty faculty) {
        this.selectedFaculty = faculty;
    }

    public void deleteFaculty(Faculty faculty) {
        facultyService.deleteFaculty(faculty);
        refreshFaculties();
    }


    public void prepareAdd() {
        resetForm();
    }

    private void refreshFaculties() {
        facultyList = facultyService.findAll();
    }

    private void resetForm() {
        selectedFaculty = new Faculty();
    }

    // Getters
    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public Faculty getSelectedFaculty() {
        return selectedFaculty;
    }
}
