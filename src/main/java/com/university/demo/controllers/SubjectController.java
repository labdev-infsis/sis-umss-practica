package com.university.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.university.demo.models.Subject;
import com.university.demo.services.SubjectService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects/list";
    }

    @GetMapping("/create")
    public String createSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects/form";
    }

    @PostMapping
    public String saveSubject(@Valid @ModelAttribute Subject subject, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "subjects/form";
        }
        subjectService.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String editSubjectForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("subject", subjectService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subject id: " + id)));
        return "subjects/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable("id") Long id) {
        subjectService.deleteById(id);
        return "redirect:/subjects";
    }
}