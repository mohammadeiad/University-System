package com.ats.project.View;

import com.ats.project.model.Semester;
import com.ats.project.model.SemesterType;
import com.ats.project.service.SemesterService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SemesterBean implements Serializable {

    private final SemesterService semesterService;
    private List<Semester> semesterList;
    private Semester selectedSemester;
    private SemesterType[] semesterTypes = SemesterType.values();

    @Inject
    public SemesterBean(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @PostConstruct
    public void init() {
        refreshSemesters();
        resetForm();
    }

    public void saveSemester() {
        // Save or update semester
        semesterService.saveOrUpdateSemester(selectedSemester);
        refreshSemesters();
        resetForm();
    }

    public void editSemester(Semester semester) {
        if (semester != null && semester.getId() != null) {
            // Fetch fresh instance from DB to avoid detached object issues
            this.selectedSemester = semesterService.findById(semester.getId())
                    .orElseThrow(() -> new RuntimeException("Semester not found"));
        }
    }

    public void prepareAdd() {
        resetForm(); // ready for new semester
    }

    public void deleteSemester(Semester semester) {
        if (semester != null && semester.getId() != null) {
            semesterService.deleteSemester(semester.getId());
            refreshSemesters();
        }
    }

    private void refreshSemesters() {
        semesterList = semesterService.findAll();
    }

    private void resetForm() {
        selectedSemester = new Semester();
    }

    // Getters
    public List<Semester> getSemesterList() { return semesterList; }
    public Semester getSelectedSemester() { return selectedSemester; }
    public SemesterType[] getSemesterTypes() { return semesterTypes; }
}
