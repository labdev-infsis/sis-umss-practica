package com.university.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.demo.models.Subject;
import com.university.demo.models.Teacher;
import com.university.demo.repositories.SubjectRepository;
import com.university.demo.repositories.TeacherRepository;
import com.university.demo.services.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));

        // Establecer a null el subject de cada teacher antes de eliminar el subject
        List<Teacher> teachers = teacherRepository.findBySubject(subject);
        for (Teacher teacher : teachers) {
            teacher.setSubject(null);
            teacherRepository.save(teacher);
        }

        // Eliminar el subject
        subjectRepository.delete(subject);
    }
}