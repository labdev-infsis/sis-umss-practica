package com.university.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.demo.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}