package com.university.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.demo.models.Subject;
import com.university.demo.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findBySubject(Subject subject);
}