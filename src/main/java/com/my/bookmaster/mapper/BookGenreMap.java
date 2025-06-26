package com.my.bookmaster.mapper;

import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.service.BookGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookGenreMap {

    private final BookGenreService service;

    public BookGenre fromId(Long id) {
        return id != null ? service.getById(id) : null;
    }
}
