package com.my.bookmaster.service;

import com.my.bookmaster.model.BookAuthor;

import java.util.List;

public interface BookAuthorService {
    List<BookAuthor> getAll();

    BookAuthor getById(Long id);

    BookAuthor save(BookAuthor bookAuthor);

    BookAuthor patch(Long id, BookAuthor bookAuthor);

    void delete(Long id);
}
