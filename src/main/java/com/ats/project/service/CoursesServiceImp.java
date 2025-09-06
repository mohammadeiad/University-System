package com.ats.project.service;

import com.ats.project.repository.CoursesRepository;
import com.ats.project.model.Courses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImp implements CoursesService {
    private final CoursesRepository coursesRepository;

    public CoursesServiceImp(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Override
    public Courses createCourses(Courses courses) {
        return coursesRepository.findByName(courses.getName())
                .orElseGet(() -> coursesRepository.save(courses));
    }

    @Override
    public List<Courses> findAll() {
        return coursesRepository.findAll();
    }

    public Courses findById(Long id) {
        return coursesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Courses not found with id: " + id));
    }

    @Override
    public Courses updateCourse(Long id,Courses courses) {
        if (courses.getId() == null) {
            throw new IllegalArgumentException("Course ID cannot be null for update.");
        }

        Courses existingCourse = coursesRepository.findById(courses.getId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courses.getId()));

        // Update fields
        existingCourse.setName(courses.getName());
        existingCourse.setDescription(courses.getDescription());
        existingCourse.setFaculty(courses.getFaculty());

        return coursesRepository.save(existingCourse);
    }

    @Override
    public Courses deleteCourse(Long id) {
        Courses existingCourse = coursesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        coursesRepository.delete(existingCourse);
        return existingCourse;
    }



}
