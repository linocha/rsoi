package com.library.book.service;

import com.library.book.model.AddBookRequest;
import com.library.book.model.BookInfoResponse;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public interface BooksService {
    @Nonnull
    BookInfoResponse addBook(@Nonnull AddBookRequest request);

    @Nonnull
    BookInfoResponse getBookInfo(@Nonnull Integer id);

    @Nonnull
    List<BookInfoResponse> getAllBooks();
}
