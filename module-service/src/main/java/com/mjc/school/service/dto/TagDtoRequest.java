package com.mjc.school.service.dto;

import java.util.List;

public record TagDtoRequest(
        Long id,
        String name,
        List<Long> newsIds
) {
}
