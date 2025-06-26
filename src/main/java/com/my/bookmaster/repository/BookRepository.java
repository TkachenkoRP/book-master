package com.my.bookmaster.repository;

import com.my.bookmaster.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
