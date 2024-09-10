package com.university.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.demo.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}