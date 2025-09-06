package com.ats.project.View;

import com.ats.project.model.Major;
import com.ats.project.service.MajorService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MajorBean implements Serializable {

    private final MajorService majorService;
    private List<Major> majorList;
    private Major selectedMajor;

    @Inject
    public MajorBean(MajorService majorService) {
        this.majorService = majorService;
    }

    @PostConstruct
    public void init() {
        refreshMajors();
        resetForm();
    }

    // Save or update
    public void saveMajor() {
        if (selectedMajor != null) {
            majorService.saveMajor(selectedMajor);
            refreshMajors();
            resetForm();
        }
    }

    // Edit major
    public void editMajor(Major major) {
        if (major != null) {
            this.selectedMajor = major;
        }
    }

    // Delete major
    public void deleteMajor(Major major) throws Exception {
        if (major != null && major.getId() != 0) {
            majorService.deleteMajor(major.getId());
            refreshMajors();
        }
    }

    // Refresh list
    private void refreshMajors() {
        majorList = majorService.findAll();
    }

    // Reset form
    public void resetForm() {
        selectedMajor = new Major();
    }

    // Getters
    public List<Major> getMajorList() { return majorList; }
    public Major getSelectedMajor() { return selectedMajor; }
}
