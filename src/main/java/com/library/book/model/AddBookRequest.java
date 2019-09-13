package com.library.book.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class AddBookRequest {
    @NotNull
    private String title;

    @NotNull
    private String author;
}
