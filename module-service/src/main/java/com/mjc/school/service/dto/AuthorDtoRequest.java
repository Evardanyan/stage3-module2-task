package com.mjc.school.service.dto;

public record AuthorDtoRequest(Long id, String name) {
    public AuthorDtoRequest(String name) {
        this(null, name);
    }
}
