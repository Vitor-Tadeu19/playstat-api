package com.mvp.playstat.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PlayerRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Age is required")
        @Min(value = 5, message = "Age must be at least 5")
        @Max(value = 80, message = "Age must be at most 80")
        Integer age,

        @NotBlank(message = "Position is required")
        String position,

        @Positive(message = "Height must be positive")
        BigDecimal height,

        @Positive(message = "Weight must be positive")
        BigDecimal weight,

        @PositiveOrZero(message = "Points per game must be zero or positive")
        BigDecimal pointsPerGame,

        @PositiveOrZero(message = "Rebounds per game must be zero or positive")
        BigDecimal reboundsPerGame,

        @PositiveOrZero(message = "Assists per game must be zero or positive")
        BigDecimal assistsPerGame
) {
}
