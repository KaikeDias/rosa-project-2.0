package com.example.rosa.app.DTOs;

import jakarta.validation.constraints.*;

public record ProductDTO(
    @NotBlank
    @Size(max = 32)
    String name,

    @NotBlank
    @Size(max = 180)
    String destination,

    @NotNull
    @Min(1)
    @Max(20)
    Double profitabilityRate,

    @NotNull
    @Min(0)
    @Max(48)
    Double minimalMonths,

    @NotNull
    @PositiveOrZero
    @Max(100)
    Double administrationTax
) {}
