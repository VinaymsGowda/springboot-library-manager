package com.learning.librarysystem.repository;

import com.learning.librarysystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
}