package com.my.bookmaster.repository;

import com.my.bookmaster.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
}
