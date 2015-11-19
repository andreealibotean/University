package com.Universities.web.services;

import com.Universities.web.Dao.CourseDAO;
import com.Universities.web.converter.*;
import com.Universities.web.data.Course;
import com.Universities.web.data.Professor;
import com.Universities.web.data.Student;
import com.Universities.web.dto.CourseDTO;
import com.Universities.web.dto.ProfessorDTO;
import com.Universities.web.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andreealibotean on 11/17/2015.
 */
@Service
public class CourseService {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    CourseConverter courseConverter;

    @Autowired
    StudentConverter studentConverter;

    @Autowired
    ProfessorConverter professorConverter;

    @Autowired
    IntegerToStudent integerToStudent;

    @Autowired
    IntegerToProfessor integerToProfessor;


    public CourseDTO getCourseById(Integer idCourse) {

        Course course = courseDAO.getCourseById(idCourse);
        CourseDTO courseDTO = courseConverter.convertCourseToDTO(courseDAO.getCourseById(idCourse));
        return courseDTO;

    }

    public List<CourseDTO> getLstCourses() {

        List<Course> lstCourses = courseDAO.getLstCourses();

        List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();

        for (Course cd : lstCourses) {
            CourseDTO courseDTO = courseConverter.convertCourseToDTO(cd);
            courseDTOs.add(courseDTO);
        }

        return courseDTOs;


    }

    public void addCourse(CourseDTO courseDTO) {
        Course course = courseConverter.convertCourseDTOToCourse(courseDTO);
        courseDAO.addCourse(course);
    }

    public void updateCourse(CourseDTO courseDTO) {
        Course course = courseConverter.convertCourseDTOToCourse(courseDTO);
        courseDAO.updateCourse(course);
    }

    public String deleteCourse(Integer idCourse) {

        if (courseDAO.deleteCourse(idCourse) == true) {
            return "The course has been deleted.";
        } else {
            return "There was a problem in deleting the course.";
        }

    }

    public List<StudentDTO> listStudentsForCourse(Integer idCourse) {

        List<Student> listStudents = courseDAO.listStudentsForCourse(idCourse);
        List<StudentDTO> registeredStudents = new ArrayList<StudentDTO>();

        for (Student s : listStudents) {
            registeredStudents.add(studentConverter.convertStudentToStudentDTO(s));
        }

        return registeredStudents;
    }


    public List<ProfessorDTO> listProfessorsForCourse(Integer idCourse) {

        List<Professor> listProfessors = courseDAO.listProfessorsForCourse(idCourse);
        List<ProfessorDTO> registeredProfessors = new ArrayList<ProfessorDTO>();

        for (Professor p : listProfessors) {
            registeredProfessors.add(professorConverter.convertProfessorToProfessorDTO(p));
        }

        return registeredProfessors;
    }

    public void addStudentsForCourse( CourseDTO courseDTO) {

        //CourseDTO courseDTO = courseConverter.convertCourseToDTO(courseDAO.getCourseById(idCourse));
        Set<Integer> studentsIds = courseDTO.getStudentsIds();

        Course course = courseDAO.getCourseById(courseDTO.getIdCourse());

        Set<Student> students = new HashSet<Student>();
        for (Integer s : studentsIds) {

            students.add(integerToStudent.convert(s));
        }
        course.getStudents().addAll(students);

        courseDAO.addStudentsForCourse(course);

    }

    public void addProfessorsForCourse( CourseDTO courseDTO) {


        //CourseDTO courseDTO = courseConverter.convertCourseToDTO(courseDAO.getCourseById(idCourse));

        Set<Integer> professorsIds = courseDTO.getProfessorsIds();

        Course course = courseDAO.getCourseById(courseDTO.getIdCourse());

        Set<Professor> professors = new HashSet<Professor>();
        for (Integer p : professorsIds) {

            professors.add(integerToProfessor.convert(p));
        }
        course.getProfessors().addAll(professors);

        courseDAO.addProfessorsForCourse(course);

    }


}
