package com.university.demo.services;

import java.util.List;
import java.util.Optional;

import com.university.demo.models.Teacher;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher save(Teacher teacher);
    void deleteById(Long id);
}
