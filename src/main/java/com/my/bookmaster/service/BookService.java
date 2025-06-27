package com.my.bookmaster.service;

import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.BookFilter;

import java.util.List;

public interface BookService {
    List<Book> getAll(BookFilter filter);

    Book getById(Long id);

    Book save(Book book);

    Book patch(Long id, Book book);

    void delete(Long id);
}
