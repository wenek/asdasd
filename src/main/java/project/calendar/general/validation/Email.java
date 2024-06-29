package project.calendar.general.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Size;

@Size(max = 254)
@jakarta.validation.constraints.Email
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target(FIELD)
@Retention(RUNTIME)
public @interface Email {

    String message() default "Cannot be null and must be a valid e-mail";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}