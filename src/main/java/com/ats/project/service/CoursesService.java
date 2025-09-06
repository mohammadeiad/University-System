package com.ats.project.service;

import com.ats.project.model.Courses;

import java.util.List;

public interface CoursesService {
    Courses createCourses(Courses courses);
    List<Courses> findAll();
    Courses findById(Long id);
    Courses updateCourse(Long id,Courses courses);
    Courses deleteCourse(Long id) throws Exception;


}
