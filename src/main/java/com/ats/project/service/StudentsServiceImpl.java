package com.ats.project.service;

import com.ats.project.model.Courses;
import com.ats.project.model.Enrollment;
import com.ats.project.model.Grades;
import com.ats.project.repository.CoursesRepository;
import com.ats.project.repository.EnrollmentRepository;
import com.ats.project.repository.StudentsRepository;
import com.ats.project.model.Students;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CoursesRepository coursesRepository;


    @Autowired
    public StudentsServiceImpl(
            StudentsRepository studentsRepository,
            EnrollmentRepository enrollmentRepository,
            CoursesRepository coursesRepository) {
        this.studentsRepository = studentsRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.coursesRepository = coursesRepository;
    }

    @Transactional
    @Override
    public Students createStudent(Students student) {
        Optional<Students> existingStudentOpt = studentsRepository.findByEmail(student.getEmail());
        if (existingStudentOpt.isPresent()) {
            Students existing = existingStudentOpt.get();
            existing.setName(student.getName());
            existing.setBirthDate(student.getBirthDate());
            existing.setMobileNo(student.getMobileNo());
            existing.setGender(student.getGender());
            existing.setMajor(student.getMajor());
            existing.setFaculty(student.getFaculty());
            existing.setNationality(student.getNationality());
            existing.setSchoolAvg(student.getSchoolAvg());
            existing.setNationalId(student.getNationalId());
            existing.setCourses(student.getCourses()); // ✅ update courses if needed
            return studentsRepository.save(existing);
        }
        return studentsRepository.save(student);
    }

    @Override
    @Transactional
    public Students updateStudent(Long id,Students updatedData) {
        return studentsRepository.findById(updatedData.getId())
                .map(existing -> {
                    // --- update basic fields ---
                    existing.setName(updatedData.getName());
                    existing.setBirthDate(updatedData.getBirthDate());
                    existing.setEmail(updatedData.getEmail());
                    existing.setMobileNo(updatedData.getMobileNo());
                    existing.setGender(updatedData.getGender());
                    existing.setMajor(updatedData.getMajor());
                    existing.setFaculty(updatedData.getFaculty());
                    existing.setNationality(updatedData.getNationality());
                    existing.setSchoolAvg(updatedData.getSchoolAvg());
                    existing.setNationalId(updatedData.getNationalId());

                    // --- update ManyToMany: Courses ---
                    existing.getCourses().clear();  // remove old links
                    if (updatedData.getCourses() != null) {
                        for (Courses c : updatedData.getCourses()) {
                            Courses managedCourse = coursesRepository.findById(c.getId())
                                    .orElseThrow(() -> new RuntimeException("Course not found with ID " + c.getId()));
                            existing.getCourses().add(managedCourse);

                            // keep both sides in sync (if bidirectional)
                            managedCourse.getStudents().add(existing);
                        }
                    }

                    return studentsRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with ID " + updatedData.getId()));
    }

    @Override
    public Students deleteStudent(Long id) {
        Optional<Students> studentOpt = studentsRepository.findById(id);
        if (studentOpt.isPresent()) {
            studentsRepository.deleteById(id);
            return studentOpt.get();
        } else {
            throw new RuntimeException("Cannot delete. Student with ID " + id + " does not exist.");
        }
    }

    @Override
    public Optional<Students> findById(Long id) {
        return studentsRepository.findById(id);
    }

    @Override
    public List<Students> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Optional<Students> findByEmail(String email) {
        return studentsRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public double calculateGPA(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        if (enrollments.isEmpty()) return 0.0;

        // Map to keep the highest grade per course
        Map<Long, Grades> courseGradeMap = new HashMap<>();
        Map<Long, Courses> coursesMap = new HashMap<>();

        // Collect highest grade and the course entity for each course
        for (Enrollment e : enrollments) {
            Grades grade = e.getGrades();
            if (grade == null) continue;

            Long courseId = e.getCourses().getId();
            Courses course = e.getCourses();

            Grades existing = courseGradeMap.get(courseId);

            // If no grade yet or current grade is higher than stored one, replace it
            if (existing == null || grade.toGpaPoints() > existing.toGpaPoints()) {
                courseGradeMap.put(courseId, grade);
                coursesMap.put(courseId, course);
            }
        }

        double totalPoints = 0.0;
        int count = 0;

        // Now compute GPA only for courses where prerequisites are passed
        for (Map.Entry<Long, Grades> entry : courseGradeMap.entrySet()) {
            Long courseId = entry.getKey();
            Grades grade = entry.getValue();
            Courses course = coursesMap.get(courseId);

            if (prerequisitesPassed(studentId, course)) {
                totalPoints += grade.toGpaPoints();
                count++;
            }
        }

        double gpa = count == 0 ? 0.0 : totalPoints / count;

        // Save updated GPA
        Students student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setGpa(gpa);
        studentsRepository.save(student);

        return gpa;

    }
    private boolean prerequisitesPassed(Long studentId, Courses course) {
        for (Courses prereq : course.getPrerequisiteCourses()) {
            Enrollment prereqEnrollment = enrollmentRepository
                    .findByStudentIdAndCoursesId(studentId, prereq.getId())
                    .orElse(null);
            if (prereqEnrollment == null || prereqEnrollment.getGrades() == Grades.F) {
                return false;
            }
        }
        return true;
    }





}