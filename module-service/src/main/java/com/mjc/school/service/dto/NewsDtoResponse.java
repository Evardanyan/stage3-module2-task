package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public record NewsDtoResponse(Long id, String title, String content, LocalDateTime createDate, LocalDateTime lastUpdatedDate, Long authorId, Long tagId) {
}
