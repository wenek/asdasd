package project.calendar.general.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Min(1)
@Max(31)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Day {

    String message() default "The value of day must be from 1 to 31";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}