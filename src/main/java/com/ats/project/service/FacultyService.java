package com.ats.project.service;

import com.ats.project.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty saveFaculty(Faculty faculty);
    List<Faculty> findAll();
    Faculty findById(Long id);
    void deleteFaculty(Faculty faculty);

}
