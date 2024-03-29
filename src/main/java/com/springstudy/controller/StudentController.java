package com.springstudy.controller;

import com.springstudy.domain.Student;
import com.springstudy.exception.StudentNotFound;
import com.springstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PostLoad;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    //http://localhost:8080/spring-mvc/students/hi
    @GetMapping("/hi")
    public ModelAndView sayHi() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Hello World");
        modelAndView.addObject("messagebody", "This is a message from the controller");
        modelAndView.setViewName("hi");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAll() {
        List<Student> students = studentService.getAll();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("studentList", students);
        modelAndView.setViewName("students");
        return modelAndView;
    }
    @GetMapping("/new")
    public String getForm(@ModelAttribute("student") Student student){
        return "studentForm";

    }
    @PostMapping("/saveStudent")
    public String save(@Valid @ModelAttribute("student") Student student){

        studentService.save(student);
        return "redirect:/students";
    }
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("id") Long id){
        Student student = studentService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("studentForm");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        studentService.delete(id);
        return "redirect:/students";
    }

    @ExceptionHandler(StudentNotFound.class)
    public ModelAndView handleStudentNotFound(StudentNotFound exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("notFound");
        return modelAndView;
    }




}
