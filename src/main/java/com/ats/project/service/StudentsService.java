package com.ats.project.service;

import com.ats.project.model.Students;

import java.util.List;
import java.util.Optional;

public interface StudentsService {


    Students createStudent(Students student);
    Optional<Students> findById(Long id);

    List<Students> findAll();

    Optional<Students> findByEmail(String email);

    Students updateStudent(Long id, Students updatedData) throws Exception; // ✅ fixed to instance method
    Students deleteStudent(Long id) throws Exception;                // instance method

    double calculateGPA(Long studentId);



}