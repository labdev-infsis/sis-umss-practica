package com.university.demo.services;

import java.util.List;
import java.util.Optional;

import com.university.demo.models.Subject;

public interface SubjectService {
    List<Subject> findAll();
    Optional<Subject> findById(Long id);
    Subject save(Subject subject);
    void deleteById(Long id);
}