package com.ats.project.model;

import com.ats.project.service.EnrollmentService;

public class EnrollmentSeeder {

    public static Enrollment trySaveEnrollment(EnrollmentService service, Enrollment enrollment) {
        try {
            return service.saveEnrollment(enrollment);
        } catch (Exception e) {
            String studentName = enrollment.getStudent() != null ? enrollment.getStudent().getName() : "Unknown student";
            String courseName = enrollment.getCourses() != null ? enrollment.getCourses().getName() : "Unknown course";
            System.out.println("Enrollment failed for student: " + studentName +
                    ", course: " + courseName +
                    " → Reason: " + e.getMessage());
            return null;
        }
    }
}
