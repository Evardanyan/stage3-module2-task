package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record NewsDtoResponse(Long id, String title, String content, Date createDate, Date lastUpdatedDate, Long authorId, Long tagId) {
    public NewsDtoResponse(Long id, String title, String content, Date createDate, Long authorId) {
        this(id, title, content, createDate, null, authorId, null);
    }
}
