package com.mvp.playstat.dto;

import jakarta.validation.constraints.NotBlank;

public record TeamRequest(

        @NotBlank(message = "Name is required")
        String name,

        String city,

        String coach
) {
}