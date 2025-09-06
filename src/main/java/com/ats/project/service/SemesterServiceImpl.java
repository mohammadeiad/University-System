package com.ats.project.service;

import com.ats.project.model.Semester;
import com.ats.project.model.SemesterType;
import com.ats.project.repository.EnrollmentRepository;
import com.ats.project.repository.SemesterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository semesterRepository;
    private final EnrollmentRepository enrollmentRepository;

    public SemesterServiceImpl(SemesterRepository semesterRepository, EnrollmentRepository enrollmentRepository) {
        this.semesterRepository = semesterRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    @Override
    public Semester saveOrUpdateSemester(Semester semester) {

        Semester existing = null;

        if (semester.getId() != null) {
            // Editing an existing semester
            existing = semesterRepository.findById(semester.getId())
                    .orElseThrow(() -> new RuntimeException("Semester not found"));
        } else {
            // No ID → check if a semester with same start & end date exists
            existing = semesterRepository
                    .findByStartDateAndEndDate(semester.getStartDate(), semester.getEndDate())
                    .orElse(null);
        }

        if (existing != null) {
            // Update existing semester (edit or duplicate date)
            existing.setName(semester.getName());
            existing.setType(semester.getType());
            existing.setStartDate(semester.getStartDate());
            existing.setEndDate(semester.getEndDate());
            existing.setActive(semester.isActive());
            return semesterRepository.save(existing);
        }

        // No existing semester → create new
        return semesterRepository.save(semester);
    }


    @Override
    public List<Semester> findAll() {
        return semesterRepository.findAll();
    }

    @Override
    public Optional<Semester> findById(Long id) {
        return semesterRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteSemester(Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        // Delete dependent enrollments first to avoid foreign key errors
        enrollmentRepository.deleteBySemesterId(id);

        // Now delete semester
        semesterRepository.delete(semester);
    }

    @Override
    public Optional<Semester> findByNameAndType(String name, SemesterType type) {
        return semesterRepository.findByNameAndType(name, type);
    }
}
