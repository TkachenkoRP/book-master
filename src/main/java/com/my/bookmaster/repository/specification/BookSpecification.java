package com.my.bookmaster.repository.specification;

import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.model.BookFilter;
import com.my.bookmaster.model.BookGenre;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public interface BookSpecification {
    static Specification<Book> withFilter(BookFilter filter) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and(predicate, byTitle(filter.title()).toPredicate(root, query, criteriaBuilder));
            predicate = criteriaBuilder.and(predicate, byAuthor(filter.author()).toPredicate(root, query, criteriaBuilder));
            predicate = criteriaBuilder.and(predicate, byGenre(filter.genre()).toPredicate(root, query, criteriaBuilder));
            return predicate;
        };
    }

    static Specification<Book> byTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get(Book.Fields.title), "%" + title + "%");
        };
    }

    static Specification<Book> byAuthor(String author) {
        return (root, query, criteriaBuilder) -> {
            if (author == null || author.isEmpty() || query == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    query.from(BookAuthor.class).get(BookAuthor.Fields.name),
                    "%" + author + "%"
            );
        };
    }

    static Specification<Book> byGenre(String genre) {
        return (root, query, criteriaBuilder) -> {
            if (genre == null || genre.isEmpty() || query == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    query.from(BookGenre.class).get(BookGenre.Fields.genre),
                    "%" + genre + "%"
            );
        };
    }
}
