package com.example.bookstore.dto;

import com.example.bookstore.validation.ValidIsbn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CreateBookRequestDto(
        Long id,
        @NotNull
        String title,
        @NotNull
        String author,
        @ValidIsbn
        String isbn,
        @NotNull
        @Positive
        BigDecimal price,
        String description,
        String coverImage
) {}
