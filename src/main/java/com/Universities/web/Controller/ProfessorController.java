package com.Universities.web.Controller;

import com.Universities.web.Dao.*;
import com.Universities.web.Model.Course;
import com.Universities.web.Model.Faculty;
import com.Universities.web.Model.Professor;
import com.Universities.web.Model.ProfessorCourseHandler;
import com.Universities.web.Validator.ProfessorValidator;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreealibotean on 11/9/2015.
 */
@Controller
public class ProfessorController {
    private ProfessorDAO professorDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private FacultyDAO facultyDAO;
    private ProfessorValidator professorValidator;
    private ProfessorCourseHandlerDAO professorCourseHandlerDAO;

    @Autowired
    public ProfessorController(ProfessorDAO professorDAO, StudentDAO studentDAO, CourseDAO courseDAO, FacultyDAO facultyDAO, ProfessorCourseHandlerDAO professorCourseHandlerDAO, ProfessorValidator professorValidator) {
        this.professorDAO = professorDAO;
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;
        this.facultyDAO = facultyDAO;
        this.professorValidator = professorValidator;
        this.professorCourseHandlerDAO = professorCourseHandlerDAO;


    }

    @RequestMapping(value = {"/professors"}, method = RequestMethod.GET)
    public String getAllProfessors(Model model) {
        List<Professor> professors = professorDAO.getAllProfessors();
        model.addAttribute("professors", professors);
        return "professors";
    }

    @RequestMapping(value = "/professors/{idprofessor:.+}", method = RequestMethod.GET)
    public ModelAndView professorView(@PathVariable("idprofessor") int idprofessor) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("professorView");
        Professor professor = professorDAO.findByProfessorId(idprofessor);
        modelAndView.addObject("professor", professor);

        List<ProfessorCourseHandler> coursesOfProfessor = professorCourseHandlerDAO.findAllCoursesForProfessor(idprofessor);
        List<Course> courseList = new ArrayList<Course>();

        for (ProfessorCourseHandler professorCourseHandler : coursesOfProfessor) {
            Course course = courseDAO.findByCourseId((int) (professorCourseHandler.getIdcourse()));
            courseList.add(course);
        }
        modelAndView.addObject(courseList);


        return modelAndView;
    }

    @RequestMapping(value = "/professors/edit/{idprofessor:.+}", method = RequestMethod.GET)
    public ModelAndView setupProfessorEdit(@PathVariable("idprofessor") int idprofessor) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("professorEdit");
        Professor professor = professorDAO.findByProfessorId(idprofessor);
        modelAndView.addObject("professor", professor);

        return modelAndView;
    }

    @RequestMapping(value = "/professors/edit/{idprofessor:.+}", method = RequestMethod.POST)
    public String submitProfessorEdit(@PathVariable("idprofessor") int idprofessor, @ModelAttribute("professor") Professor professor) {
        professorDAO.update(professor, idprofessor);
        return "redirect:/universities/professors";
    }

    @RequestMapping(value = "/professors/delete/{idprofessor:.+}", method = RequestMethod.GET)
    public String deleteProfessor(@PathVariable("idprofessor") int idprofessor) {
        professorDAO.delete(idprofessor);
        return "redirect:/universities/professors";
    }

    @RequestMapping(value = "/professorForm", method = RequestMethod.GET)
    public String setupProfessorForm(Model model) {
        Professor professor = new Professor();
        //ProfessorCourseHandler professorCourseHandler=new ProfessorCourseHandler();
        //model.addAttribute("professorCourseHandler",professorCourseHandler);
        model.addAttribute("professor", professor);
        dropDownFaculties(model);
        //checkboxCourses(model);
        return "professorForm";
    }


    @RequestMapping(value = "/professorForm", method = RequestMethod.POST)
    public String submitProfessorForm(@ModelAttribute("professor") Professor professor, BindingResult result, SessionStatus status) {

        professorValidator.validate(professor, result);
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("professorForm");
            modelAndView.addObject("professor", professor);
            return modelAndView.getViewName();
        }

        professorDAO.insert(professor);
        status.isComplete();
        return "redirect:professors";
    }

    private void dropDownFaculties(Model model) {
        List<Faculty> facultyList = facultyDAO.getAllFaculties();
        model.addAttribute("faculties", facultyList);
    }


}
