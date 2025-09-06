package com.ats.project.repository;

import com.ats.project.model.Semester;
import com.ats.project.model.SemesterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Optional<Semester> findByName(String name);
    Optional<Semester> findByNameAndType(String name, SemesterType type);
    Optional<Semester> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);





}
