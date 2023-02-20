package com.mjc.school.service.dto;

import com.mjc.school.repository.model.impl.TagModel;

import java.util.Date;
import java.util.List;

public record NewsDtoResponse(Long id, String title, String content, Date createDate, Date lastUpdatedDate, Long authorId, List<TagModel> tagList) {
    public NewsDtoResponse(Long id, String title, String content, Date createDate, Long authorId) {
        this(id, title, content, null, null, authorId, null);
    }
}
