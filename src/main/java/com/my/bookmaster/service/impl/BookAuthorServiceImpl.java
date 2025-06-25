package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.mapper.BookAuthorMapper;
import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.repository.BookAuthorRepository;
import com.my.bookmaster.service.BookAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorRepository repository;
    private final BookAuthorMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<BookAuthor> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public BookAuthor getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Автор с ID {0} не найден!", id
        )));
    }

    @Transactional
    @Override
    public BookAuthor save(BookAuthor bookAuthor) {
        return repository.save(bookAuthor);
    }

    @Transactional
    @Override
    public BookAuthor patch(Long id, BookAuthor bookAuthor) {
        BookAuthor existed = getById(id);
        mapper.pathBookAuthor(bookAuthor, existed);
        return repository.save(existed);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
