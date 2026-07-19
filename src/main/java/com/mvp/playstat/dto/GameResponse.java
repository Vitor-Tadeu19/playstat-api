package com.mvp.playstat.dto;

import com.mvp.playstat.model.enums.GameStatus;

import java.time.LocalDateTime;

public record GameResponse(
        Long id,
        TeamSummaryResponse homeTeam,
        TeamSummaryResponse awayTeam,
        LocalDateTime gameDate,
        Integer homeScore,
        Integer awayScore,
        GameStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
