package com.library.book.repository;

import com.library.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

public interface BooksRepository
        extends JpaRepository<Book, Integer>{

    Optional<Book> findById(@Nonnull Integer id);
}
