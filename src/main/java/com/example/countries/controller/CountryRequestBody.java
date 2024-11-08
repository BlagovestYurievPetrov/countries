package com.example.countries.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record CountryRequestBody(@NotEmpty String startingCountry,
                                 @NotEmpty @Min(0) int budgetPerCountry,
                                 @NotEmpty @Min(0) int totalBudget,
                                 @NotEmpty String currency) {
}
