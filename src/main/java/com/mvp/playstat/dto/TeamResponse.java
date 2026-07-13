package com.mvp.playstat.dto;

import java.time.LocalDateTime;

public record TeamResponse(
        Long id,
        String name,
        String city,
        String coach,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}