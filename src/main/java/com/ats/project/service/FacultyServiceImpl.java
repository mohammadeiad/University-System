package com.ats.project.service;

import com.ats.project.repository.FacultyRepository;
import com.ats.project.model.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.findByName(faculty.getName())
                .orElseGet(() -> facultyRepository.save(faculty));
    }

    @Override
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + id));
    }

    @Override
    public void deleteFaculty(Faculty faculty) {
        facultyRepository.delete(faculty);
    }

}

