package com.library.book.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class BookInfoResponse {
    private Integer id;
    private UUID bookUid;
    private String title;
    private String author;
}
