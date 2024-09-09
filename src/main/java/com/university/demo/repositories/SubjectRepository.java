package com.university.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.demo.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}