package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.mapper.BookGenreMapper;
import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.repository.BookGenreRepository;
import com.my.bookmaster.service.BookGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookGenreServiceImpl implements BookGenreService {

    private final BookGenreRepository repository;
    private final BookGenreMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<BookGenre> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public BookGenre getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Жанр книги с ID {0} не найден!", id
        )));
    }

    @Transactional
    @Override
    public BookGenre save(BookGenre bookGenre) {
        return repository.save(bookGenre);
    }

    @Transactional
    @Override
    public BookGenre patch(Long id, BookGenre bookGenre) {
        BookGenre existed = getById(id);
        mapper.pathBookGenre(bookGenre, existed);
        return repository.save(existed);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
