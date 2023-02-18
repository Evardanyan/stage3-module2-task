package com.mjc.school.service.dto;

public record TagDtoRequest(Long id, String name) {
    public TagDtoRequest(String name) {
        this(null, name);
    }
}
