package com.silva.rodrigues.marcos.authjwt.dto;

public record TokenDto(
        String value,
        String type,
        String expiration
) {
}
