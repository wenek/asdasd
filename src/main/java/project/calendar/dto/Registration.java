package project.calendar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import project.calendar.general.validation.Email;
import project.calendar.general.validation.Equals;
import project.calendar.general.validation.ReqPassword;

@Equals(
        first = "password",
        second = "passwordConfirmation",
        message = "Password and password confirmation contain different values")
public record Registration(
        @NotEmpty(message = "E-mail cannot be empty")
        @Email(message = "The provided e-mail is invalid")
        String email,
        @ReqPassword
        String password,
        @NotBlank(message = "Password confirmation cannot be empty")
        String passwordConfirmation) {

    public static Registration empty() {
        return new Registration(null, null, null);
    }

}
