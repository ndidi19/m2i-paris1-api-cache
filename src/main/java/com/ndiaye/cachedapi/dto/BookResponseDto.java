package com.ndiaye.cachedapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {

    private String title;
    private String author;
    private int year;
    private String country;
}
