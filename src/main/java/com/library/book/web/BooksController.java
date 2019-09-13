package com.library.book.web;

import com.library.book.model.AddBookRequest;
import com.library.book.model.BookInfoResponse;
import com.library.book.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public List<BookInfoResponse> getBooks() {
        return booksService.getAllBooks();
    }

    @PostMapping(
            consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public BookInfoResponse addBook(@RequestBody @Valid AddBookRequest request) {
        return booksService.addBook(request);
    }

    @GetMapping(
            path = "/{id}",
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public BookInfoResponse getBookInfo(@PathVariable Integer id) {
        return booksService.getBookInfo(id);
    }
}
