package com.example.simpleproject.repository;

import com.example.simpleproject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {

    BookEntity findByISNBcode(String isbn);
    BookEntity findByName(String name);
}
