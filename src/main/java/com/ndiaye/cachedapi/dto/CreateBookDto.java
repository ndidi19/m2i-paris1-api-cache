package com.ndiaye.cachedapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {

    @NotEmpty(message = "Should not be empty")
    private String title;
    @NotNull(message = "Should not be null")
    private String author;
    @Min(value = 1500, message = "Must be equal or greater than 1500")
    @Max(value = 2022, message = "Must be equal or less than 2022")
    private int year;
    private String country;
}
