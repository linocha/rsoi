package com.library.book;

import com.library.book.web.BooksController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import com.library.book.web.BooksController;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test-db")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BooksApplicationTest {

    @Autowired
    private BooksController booksController;

    @Test
    void testApplicationStarted() {
        assertNotNull(booksController);
    }
}
