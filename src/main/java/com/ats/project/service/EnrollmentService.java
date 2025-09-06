package com.ats.project.service;

import com.ats.project.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment saveEnrollment(Enrollment enrollment);
    List<Enrollment> findByStudentId(Long studentId);
    void deleteEnrollment(Long enrollmentId);
    List<Enrollment> findAll();
    List<Enrollment> getEnrollmentsByStudentId(Long studentId);



}