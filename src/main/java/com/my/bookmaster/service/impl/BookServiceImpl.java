package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.mapper.BookMapper;
import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.BookFilter;
import com.my.bookmaster.repository.BookRepository;
import com.my.bookmaster.repository.specification.BookSpecification;
import com.my.bookmaster.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll(BookFilter filter) {
        Specification<Book> spec = BookSpecification.withFilter(filter);
        return repository.findAll(spec);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Книга с ID {0} не найдена!", id
        )));
    }

    @Transactional
    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Transactional
    @Override
    public Book patch(Long id, Book book) {
        Book existed = getById(id);
        mapper.pathBook(book, existed);
        return repository.save(existed);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
