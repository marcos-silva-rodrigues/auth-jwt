package com.silva.rodrigues.marcos.authjwt.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateTaskDto(

        @NotBlank
        @Length(max = 255, min = 16)
        String title,

        @NotBlank
        @Length(max = 255, min = 60)
        String description
) {
}
