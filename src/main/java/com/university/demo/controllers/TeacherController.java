package com.university.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.university.demo.models.Teacher;
import com.university.demo.services.SubjectService;
import com.university.demo.services.TeacherService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

     @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teachers/list";
    }

    @GetMapping("/create")
    public String createTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("subjects", subjectService.findAll());
        return "teachers/form";
    }

    @PostMapping
    public String saveTeacher(@Valid @ModelAttribute Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("subjects", subjectService.findAll());
            return "teachers/form";
        }
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editTeacherForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("teacher", teacherService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher id: " + id)));
        model.addAttribute("subjects", subjectService.findAll());
        return "teachers/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteById(id);
        return "redirect:/teachers";
    }
}
