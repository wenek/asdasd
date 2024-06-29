package project.calendar.general.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;

@NotNull
@Password
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target(FIELD)
@Retention(RUNTIME)
public @interface ReqPassword {

    String message() default "Password must consist of: " +
            "minimum 8 characters, " +
            "at least one lowercase and uppercase letter, " +
            "digit and a special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
