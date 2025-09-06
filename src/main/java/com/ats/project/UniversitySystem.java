package com.ats.project;

import com.ats.project.model.*;
import com.ats.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class UniversitySystem extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private NationalityService nationalityService;
	@Autowired
	private StudentsService studentsService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private CoursesService coursesService;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private EnrollmentService enrollmentService;

	public UniversitySystem(NationalityService nationalityService,
							StudentsService studentsService,
							MajorService majorService,
							FacultyService facultyService,
							CoursesService coursesService,
							SemesterService semesterService,
							EnrollmentService enrollmentService) {
		this.nationalityService = nationalityService;
		this.studentsService = studentsService;
		this.majorService = majorService;
		this.facultyService = facultyService;
		this.coursesService = coursesService;
		this.semesterService = semesterService;
		this.enrollmentService = enrollmentService;
	}

	// This method is added for WAR deployment
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UniversitySystem.class);
	}

	@Override
	public void run(String... args) throws Exception {
		// Your existing CommandLineRunner code for data seeding goes here
		Nationality nationality = new Nationality();
		nationality.setName("Jordanian");
		nationality = nationalityService.saveNationality(nationality);

		Major major1 = new Major();
		major1.setName("CS");
		major1 = majorService.saveMajor(major1);

		Major major2 = new Major();
		major2.setName("SE");
		major2 = majorService.saveMajor(major2);

		Faculty faculty = new Faculty();
		faculty.setName("IT");
		faculty = facultyService.saveFaculty(faculty);

		Faculty faculty2 = new Faculty();
		faculty2.setName("Science");
		faculty2 = facultyService.saveFaculty(faculty2);


		Students students1 = new Students();
		students1.setName("Mohammad");
		students1.setNationalId("2000000000");
		students1.setGender("Male");
		students1.setBirthDate(LocalDate.of(2003, 7, 20));
		students1.setSchoolAvg(93);
		students1.setMobileNo("0790944108");
		students1.setEmail("Mohammadeiada@gmail.com");
		students1.setNationality(nationality);
		students1.setMajor(major1);
		students1.setFaculty(faculty);
		students1 = studentsService.createStudent(students1);

		Students students2 = new Students();
		students2.setName("Hamza");
		students2.setNationalId("2111111111");
		students2.setGender("Male");
		students2.setBirthDate(LocalDate.of(1998, 11, 30));
		students2.setSchoolAvg(90);
		students2.setMobileNo("0790000012");
		students2.setEmail("Hamzaeiada@gmail.com");
		students2.setNationality(nationality);
		students2.setMajor(major2);
		students2.setFaculty(faculty);
		students2 = studentsService.createStudent(students2);

		Students students3 = new Students();
		students3.setName("Adle");
		students3.setNationalId("2222222222");
		students3.setGender("Male");
		students3.setBirthDate(LocalDate.of(2003, 10, 1));
		students3.setSchoolAvg(82);
		students3.setMobileNo("0793450012");
		students3.setEmail("Adel@gmail.com");
		students3.setNationality(nationality);
		students3.setMajor(major1);
		students3.setFaculty(faculty);
		students3 = studentsService.createStudent(students3);

		Students students4 = new Students();
		students4.setName("Leen");
		students4.setNationalId("1900000000");
		students4.setGender("Female");
		students4.setBirthDate(LocalDate.of(2001, 4, 22));
		students4.setSchoolAvg(98);
		students4.setMobileNo("0790922341");
		students4.setEmail("leen@gmail.com");
		students4.setNationality(nationality);
		students4.setMajor(major2);
		students4.setFaculty(faculty);
		students4 = studentsService.createStudent(students4);

		Students students5 = new Students();
		students5.setName("Sarah");
		students5.setNationalId("2000712321");
		students5.setGender("Female");
		students5.setBirthDate(LocalDate.of(2003, 4, 22));
		students5.setSchoolAvg(97);
		students5.setMobileNo("0781922341");
		students5.setEmail("sarah@gmail.com");
		students5.setNationality(nationality);
		students5.setMajor(major2);
		students5.setFaculty(faculty);
		students5 = studentsService.createStudent(students5);






		Students updatedstudent1=new Students();
		updatedstudent1.setName("eiad");
		updatedstudent1.setNationalId("211110020");
		updatedstudent1.setGender("male");
		updatedstudent1.setBirthDate(LocalDate.of(1969, 11, 30));
		updatedstudent1.setSchoolAvg(95);
		updatedstudent1.setMobileNo("0791234512");
		updatedstudent1.setEmail("eiad11@gmail.com");
		updatedstudent1.setNationality(nationality);
		updatedstudent1.setMajor(major1);
		updatedstudent1.setFaculty(faculty);

		Students updatedstudent2=new Students();
		updatedstudent2.setName("mohammad");
		updatedstudent2.setNationalId("2000706848");
		updatedstudent2.setGender("male");
		updatedstudent2.setBirthDate(LocalDate.of(2003, 7, 20));
		updatedstudent2.setSchoolAvg(95);
		updatedstudent2.setMobileNo("0798783589");
		updatedstudent2.setEmail("mohammd@gmail.com");
		updatedstudent2.setNationality(nationality);
		updatedstudent2.setMajor(major1);
		updatedstudent2.setFaculty(faculty);

		Courses course1 = new Courses();
		course1.setName("OOP");
		course1.setCreditHours(60);
		course1.setActive(true);
		course1.setDescription("OOP Programming language class");
		course1.setFaculty(faculty);
		course1 = coursesService.createCourses(course1);


		Courses course2 = new Courses();
		course2.setName("Python");
		course2.setCreditHours(60);
		course2.setActive(true);
		course2.setDescription("Pyhton Programming language class");
		course2.setFaculty(faculty);
		course2 = coursesService.createCourses(course2);

		Courses course3 = new Courses();
		course3.setName("JAVA");
		course3.setCreditHours(60);
		course3.setActive(true);
		course3.setDescription(" JAVA Programming language class");
		course3.setFaculty(faculty);
		course3.getPrerequisiteCourses().add(course1);
		course3 = coursesService.createCourses(course3);



		students1.setCourses(new ArrayList<>(List.of(course2,course1,course3)));
		studentsService.updateStudent(students1.getId(), students1);

		students2.setCourses(new ArrayList<>(List.of(course2,course1,course3)));
		studentsService.updateStudent(students2.getId(), students2);

		students3.setCourses(new ArrayList<>(List.of(course2,course1,course3)));
		studentsService.updateStudent(students3.getId(), students3);

		students4.setCourses(new ArrayList<>(List.of(course2,course1,course3)));
		studentsService.updateStudent(students4.getId(), students4);

		students5.setCourses(new ArrayList<>(List.of(course2,course1,course3)));
		studentsService.updateStudent(students5.getId(), students5);


		course1.setStudents(Arrays.asList( students1,students2, students3, students4,students5));
		coursesService.createCourses(course1);
		course2.setStudents(Arrays.asList( students1,students2,students3,students4, students5));
		coursesService.createCourses(course2);
		course3.setStudents(Arrays.asList( students1,students2,students3,students4,students5));
		coursesService.createCourses(course3);




		Semester semester1 = new Semester();
		semester1.setActive(true);
		semester1.setType(SemesterType.SUMMER);
		semester1.setStartDate(LocalDate.of(2025, 7, 13));
		semester1.setEndDate(LocalDate.of(2025, 9, 13));
		semester1.setName("Summer of 2025");
		semester1 = semesterService.saveOrUpdateSemester(semester1);

		Semester semester2 = new Semester();
		semester2.setActive(true);
		semester2.setType(SemesterType.WINTER);
		semester2.setStartDate(LocalDate.of(2025, 10, 7));
		semester2.setEndDate(LocalDate.of(2026, 1, 28));
		semester2.setName("Winter 2025/2026");
		semester2 = semesterService.saveOrUpdateSemester(semester2);

		Enrollment enrollment1 = new Enrollment();
		enrollment1.setEnrollmentDate(LocalDate.of(2025, 7, 7));
		enrollment1.setSemester(semester1);
		enrollment1.setCourses(course1);
		enrollment1.setStudent(students1);
		enrollment1.setGrades(Grades.A_plus);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment1);


		Enrollment enrollment2= new Enrollment();
		enrollment2.setEnrollmentDate(LocalDate.of(2025, 7, 9));
		enrollment2.setSemester(semester1);
		enrollment2.setCourses(course2);
		enrollment2.setStudent(students1);
		enrollment2.setGrades(Grades.A);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment2);



		Enrollment enrollment3 = new Enrollment();
		enrollment3.setEnrollmentDate(LocalDate.of(2025, 7, 7));
		enrollment3.setSemester(semester1);
		enrollment3.setCourses(course3);
		enrollment3.setStudent(students1);
		enrollment3.setGrades(Grades.A_plus);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment3);


		Enrollment enrollment4 = new Enrollment();
		enrollment4.setEnrollmentDate(LocalDate.of(2025, 7, 9));
		enrollment4.setSemester(semester1);
		enrollment4.setCourses(course1);
		enrollment4.setStudent(students2);
		enrollment4.setGrades(Grades.F);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment4);

		Enrollment enrollment5 = new Enrollment();
		enrollment5.setEnrollmentDate(LocalDate.of(2025, 7, 7));
		enrollment5.setSemester(semester1);
		enrollment5.setCourses(course2);
		enrollment5.setStudent(students2);
		enrollment5.setGrades(Grades.D);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment5);


		Enrollment enrollment6 = new Enrollment();
		enrollment6.setEnrollmentDate(LocalDate.of(2025, 7, 9));
		enrollment6.setSemester(semester1);
		enrollment6.setCourses(course3);
		enrollment6.setStudent(students2);
		enrollment6.setGrades(Grades.A_plus);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment6);

		Enrollment enrollment7 = new Enrollment();
		enrollment7.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment7.setSemester(semester1);
		enrollment7.setCourses(course1);
		enrollment7.setStudent(students3);
		enrollment7.setGrades(Grades.B_plus);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment7);

		Enrollment enrollment8 = new Enrollment();
		enrollment8.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment8.setSemester(semester1);
		enrollment8.setCourses(course2);
		enrollment8.setStudent(students3);
		enrollment8.setGrades(Grades.B);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment8);

		Enrollment enrollment9 = new Enrollment();
		enrollment9.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment9.setSemester(semester1);
		enrollment9.setCourses(course3);
		enrollment9.setStudent(students3);
		enrollment9.setGrades(Grades.A);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment9);

		Enrollment enrollment10= new Enrollment();
		enrollment10.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment10.setSemester(semester1);
		enrollment10.setCourses(course1);
		enrollment10.setStudent(students4);
		enrollment10.setGrades(Grades.C_plus);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment10);


		Enrollment enrollment11 = new Enrollment();
		enrollment11.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment11.setSemester(semester1);
		enrollment11.setCourses(course2);
		enrollment11.setStudent(students4);
		enrollment11.setGrades(Grades.B_plus);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment11);


		Enrollment enrollment12 = new Enrollment();
		enrollment12.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment12.setSemester(semester1);
		enrollment12.setCourses(course3);
		enrollment12.setStudent(students4);
		enrollment12.setGrades(Grades.C);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment12);

		Enrollment enrollment13 = new Enrollment();
		enrollment13.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment13.setSemester(semester1);
		enrollment13.setCourses(course1);
		enrollment13.setStudent(students5);
		enrollment13.setGrades(Grades.A);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment13);


		Enrollment enrollment14 = new Enrollment();
		enrollment14.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment14.setSemester(semester1);
		enrollment14.setCourses(course2);
		enrollment14.setStudent(students5);
		enrollment14.setGrades(Grades.A);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment14);

		Enrollment enrollment15 = new Enrollment();
		enrollment15.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment15.setSemester(semester1);
		enrollment15.setCourses(course3);
		enrollment15.setStudent(students5);
		enrollment15.setGrades(Grades.A);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment15);

		Enrollment enrollment16 = new Enrollment();
		enrollment16.setEnrollmentDate(LocalDate.of(2025, 10, 7));
		enrollment16.setSemester(semester2);
		enrollment16.setCourses(course1);
		enrollment16.setStudent(students2);
		enrollment16.setGrades(Grades.A_plus);
		EnrollmentSeeder.trySaveEnrollment(enrollmentService,enrollment16);


		students1.setGpa(studentsService.calculateGPA(students1.getId()));
		studentsService.createStudent(students1);

		students2.setGpa(studentsService.calculateGPA(students2.getId()));
		studentsService.createStudent(students2);

		students3.setGpa(studentsService.calculateGPA(students3.getId()));
		studentsService.createStudent(students3);

		students4.setGpa(studentsService.calculateGPA(students4.getId()));
		studentsService.createStudent(students4);

		students5.setGpa(studentsService.calculateGPA(students5.getId()));
		studentsService.createStudent(students5);


//		studentsService.updateStudent(students2.getId(), updatedstudent1);
//		studentsService.updateStudent(students1.getId(), updatedstudent2);




		List<Students> students = studentsService.findAll();
		System.out.println("All Students in DB:");
		students.forEach(s -> System.out.println(" - " + s.getName() + " | " + s.getEmail()));
	}


	public static void main(String[] args) {
		SpringApplication.run(UniversitySystem.class, args);
	}
}