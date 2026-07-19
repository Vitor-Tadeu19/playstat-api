package com.mvp.playstat.dto;

import com.mvp.playstat.model.enums.GameStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record GameRequest(
        @NotNull(message = "Home team id is required")
        Long homeTeamId,

        @NotNull(message = "Away team id is required")
        Long awayTeamId,

        @NotNull(message = "Game date is required")
        LocalDateTime gameDate,

        @PositiveOrZero(message = "Home score must be zero or positive")
        Integer homeScore,

        @PositiveOrZero(message = "Away score must be zero or positive")
        Integer awayScore,

        GameStatus status
) {
}
