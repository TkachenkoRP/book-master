package com.my.bookmaster.mapper;

import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.service.BookAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookAuthorMap {

    private final BookAuthorService service;

    public BookAuthor fromId(Long id) {
        return id != null ? service.getById(id) : null;
    }
}
