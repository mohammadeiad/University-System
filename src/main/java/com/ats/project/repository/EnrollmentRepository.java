package com.ats.project.repository;

import com.ats.project.model.Courses;
import com.ats.project.model.Enrollment;
import com.ats.project.model.Students;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
     List<Enrollment> findByStudentId(Long studentId) ;
    Optional<Enrollment> findByStudentIdAndCoursesIdAndSemesterId(Long studentId, Long courseId, Long semesterId);
//    @EntityGraph(attributePaths = {"prerequisiteCourses"})
    Optional<Enrollment> findByStudentIdAndCoursesId(Long studentId, Long courseId);

    void deleteBySemesterId(Long semesterId);



}
