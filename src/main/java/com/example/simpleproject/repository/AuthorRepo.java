package com.example.simpleproject.repository;

import com.example.simpleproject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Author findByName(String name);
}
