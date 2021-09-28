package com.example.simpleproject.repository;

import com.example.simpleproject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {

    BookEntity findByISNBcode(String isbn);
    BookEntity findByName(String name);
    @Query(value = "select * from Public.books join Public.book_authors on id = book_id join Public.author on author_id = author.id where author.name =" + ":author",nativeQuery = true)
    List<BookEntity> findAllByAuthors(@Param("author") String author);
}
