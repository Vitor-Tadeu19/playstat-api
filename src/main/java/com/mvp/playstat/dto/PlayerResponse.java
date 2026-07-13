package com.mvp.playstat.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlayerResponse(
        Long id,
        String name,
        Integer age,
        String position,
        BigDecimal height,
        BigDecimal weight,
        BigDecimal pointsPerGame,
        BigDecimal reboundsPerGame,
        BigDecimal assistsPerGame,
        TeamSummaryResponse team,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
