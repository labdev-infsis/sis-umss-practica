package com.university.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.demo.models.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}