package com.ats.project.repository;

import com.ats.project.model.Courses;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

    @EntityGraph(attributePaths = {"prerequisiteCourses"})
    Optional<Courses> findByName(String name);


}
