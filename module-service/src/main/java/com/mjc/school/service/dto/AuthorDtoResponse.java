package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record AuthorDtoResponse(Long id, String name, Date createDate, Date lastUpdatedDate) {
}
