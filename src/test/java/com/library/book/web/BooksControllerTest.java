package com.library.book.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.library.book.model.BookInfoResponse;
import com.library.book.model.AddBookRequest;
import com.library.book.service.BooksService;

import java.net.URI;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class BooksControllerTest {
    private static final String TITLE1 = "In Search of Lost Time";
    private static final String AUTHOR1 = "Marcel Proust";
    private static final Integer ID1 = 11;
    private static final String TITLE2 = "The Great Gatsby";
    private static final String AUTHOR2 = "F. Scott Fitzgerald";
    private static final Integer ID2 = 12;

    @MockBean
    private BooksService booksService;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new GsonBuilder().create();

    @Test
    void booksSuccess()
            throws Exception {

        final BookInfoResponse book1 = new BookInfoResponse()
                .setId(ID1)
                .setBookUid(UUID.randomUUID())
                .setTitle(TITLE1)
                .setAuthor(AUTHOR1);
        final BookInfoResponse book2 = new BookInfoResponse()
                .setId(ID2)
                .setBookUid(UUID.randomUUID())
                .setTitle(TITLE2)
                .setAuthor(AUTHOR2);

        when(booksService.getAllBooks())
                .thenReturn(newArrayList(book1, book2));

        mockMvc.perform(get("/books")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value(TITLE1))
                .andExpect(jsonPath("$[0].author").value(AUTHOR1))
                .andExpect(jsonPath("$[1].title").value(TITLE2))
                .andExpect(jsonPath("$[1].author").value(AUTHOR2));
    }

    @Test
    void booksFindSuccess()
            throws Exception {
        final BookInfoResponse book = new BookInfoResponse()
                .setId(ID1)
                .setBookUid(UUID.randomUUID())
                .setTitle(TITLE1)
                .setAuthor(AUTHOR1);
        when(booksService.getBookInfo(eq(ID1))).thenReturn(book);

        mockMvc.perform(get("/books/" + ID1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.title").value(TITLE1))
                .andExpect(jsonPath("$.author").value(AUTHOR1));
    }




}
