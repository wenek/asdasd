package project.calendar.dto;

import jakarta.validation.constraints.NotNull;
import project.calendar.general.validation.Email;
import project.calendar.general.validation.Equals;
import project.calendar.general.validation.Password;

@Equals(
        first = "password",
        second = "passwordConfirmation")
public record Registration(
        @NotNull @Email String email,
        @NotNull @Password String password,
        @NotNull @Password String passwordConfirmation) {}
