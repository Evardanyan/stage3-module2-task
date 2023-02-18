package com.mjc.school.service.dto;

public record NewsDtoRequest(Long id, String title, String content, Long authorId, Long tagId) {
}
