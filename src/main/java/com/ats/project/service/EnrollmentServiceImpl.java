package com.ats.project.service;

import com.ats.project.model.Courses;
import com.ats.project.model.Enrollment;
import com.ats.project.model.Grades;
import com.ats.project.model.Students;
import com.ats.project.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentsService studentsService; // ✅ Inject StudentsService

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Transactional
    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        Long studentId = enrollment.getStudent().getId();
        Long courseId = enrollment.getCourses().getId();
        Long semesterId = enrollment.getSemester().getId();

        // Check if student already enrolled in same course & semester
        Enrollment existingEnrollment = enrollmentRepository
                .findByStudentIdAndCoursesIdAndSemesterId(studentId, courseId, semesterId)
                .orElse(null);

        // Check prerequisites
        Courses course = enrollment.getCourses();
        Students student = enrollment.getStudent();

        for (Courses prerequisite : course.getPrerequisiteCourses()) {
            Optional<Enrollment> prereqEnrollment = enrollmentRepository
                    .findByStudentIdAndCoursesId(student.getId(), prerequisite.getId());

            if (prereqEnrollment.isEmpty() ||
                    prereqEnrollment.get().getGrades() == Grades.F ||
                    !prereqEnrollment.get().getGrades().isPassing()) {
                throw new IllegalStateException(
                        "Cannot enroll: prerequisite course '" + prerequisite.getName() + "' not passed.");
            }
        }

        Enrollment saved;
        if (existingEnrollment != null) {
            // Update existing enrollment
            existingEnrollment.setGrades(enrollment.getGrades());
            existingEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
            existingEnrollment.setSemester(enrollment.getSemester());
            saved = enrollmentRepository.save(existingEnrollment);
        } else {
            // Save new enrollment
            saved = enrollmentRepository.save(enrollment);
        }

        // ✅ Recalculate GPA for this student automatically
        studentsService.calculateGPA(studentId);

        return saved;
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

}
