package com.my.bookmaster.service;

import com.my.bookmaster.model.BookGenre;

import java.util.List;

public interface BookGenreService {
    List<BookGenre> getAll();

    BookGenre getById(Long id);

    BookGenre save(BookGenre bookGenre);

    BookGenre patch(Long id, BookGenre bookGenre);

    void delete(Long id);
}
