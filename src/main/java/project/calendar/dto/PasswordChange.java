package project.calendar.dto;

import jakarta.validation.constraints.NotBlank;
import project.calendar.general.validation.Equals;
import project.calendar.general.validation.Password;

@Equals(
        first = "newPassword",
        second = "newPasswordConfirmation")
public record PasswordChange(
        @NotBlank String currentPassword,
        @NotBlank @Password String newPassword,
        @NotBlank @Password String newPasswordConfirmation) {}
