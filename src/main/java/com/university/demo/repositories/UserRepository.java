package com.university.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}