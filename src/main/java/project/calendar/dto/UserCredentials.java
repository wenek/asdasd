package project.calendar.dto;

import jakarta.validation.constraints.NotNull;
import project.calendar.general.validation.Email;

public record UserCredentials(
        @NotNull @Email String email,
        @NotNull String password) {}
