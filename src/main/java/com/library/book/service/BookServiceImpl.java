package com.library.book.service;

import com.library.book.domain.Book;
import com.library.book.model.AddBookRequest;
import com.library.book.model.BookInfoResponse;
import com.library.book.repository.BooksRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class BookServiceImpl
        implements BooksService {

    private static final Logger logger = getLogger(BookServiceImpl.class);
    private final BooksRepository booksRepository;

    @Autowired
    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Nonnull
    @Override
    @Transactional
    public BookInfoResponse addBook(@Nonnull AddBookRequest request) {
        final Book book = buildBook(request);

        booksRepository.save(book);

        logger.info("Added new book with title '{}'", book.getTitle());

        return buildBookInfoResponse(book);
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public BookInfoResponse getBookInfo(@Nonnull Integer id) {
        return booksRepository
                .findById(id)
                .map(this::buildBookInfoResponse)
                .orElseThrow(() -> new EntityNotFoundException(format("No book with uid '%s'", id)));

    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<BookInfoResponse> getAllBooks() {
        return booksRepository.findAll()
                .stream()
                .map(this::buildBookInfoResponse)
                .collect(Collectors.toList());
    }

    @Nonnull
    private Book buildBook(@Nonnull AddBookRequest request) {
        return new Book()
                .setBookUid(UUID.randomUUID())
                .setTitle(request.getTitle())
                .setAuthor(request.getAuthor());
    }

    @Nonnull
    private BookInfoResponse buildBookInfoResponse(@Nonnull Book book) {

        return new BookInfoResponse()
                .setId(book.getId())
                .setBookUid(book.getBookUid())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor());
    }
}
