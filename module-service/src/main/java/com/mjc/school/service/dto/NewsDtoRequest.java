package com.mjc.school.service.dto;

public record NewsDtoRequest(Long id, String title, String content, Long authorId, Long tagId) {
    public NewsDtoRequest(String title, String content, Long authorId) {
        this(null, title, content, authorId, null);
    }

    public NewsDtoRequest(Long id, String title, String content, Long authorId) {
        this(id, title, content, authorId, null);
    }

}
