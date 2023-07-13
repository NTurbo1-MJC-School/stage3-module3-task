package com.mjc.school.service.dto;

import java.util.List;

public record NewsDtoRequest(
    Long id,
    String title,
    String content,
    Long authorId,
    List<Long> tagIds
) {
}
