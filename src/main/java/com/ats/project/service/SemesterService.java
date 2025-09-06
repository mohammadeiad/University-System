package com.ats.project.service;

import com.ats.project.model.Semester;
import com.ats.project.model.SemesterType;

import java.util.List;
import java.util.Optional;

public interface SemesterService {

   // Semester SaveSemester(Semester semester);
    List<Semester> findAll(); // Added method to find all semesters
    Optional<Semester> findById(Long id); // Added method to find a semester by its ID
    Semester saveOrUpdateSemester(Semester semester);
    Optional<Semester> findByNameAndType(String name, SemesterType type);
    void deleteSemester(Long id);



}
