package com.silva.rodrigues.marcos.authjwt.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String bio
) {
}
