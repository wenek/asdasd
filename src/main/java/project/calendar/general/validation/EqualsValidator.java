package project.calendar.general.validation;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EqualsValidator implements ConstraintValidator<Equals, Object> {

    private Equals annotation;

    @Override
    public void initialize(Equals constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Field firstField = o.getClass().getDeclaredField(annotation.first());
            Field secondField = o.getClass().getDeclaredField(annotation.second());

            firstField.setAccessible(true);
            secondField.setAccessible(true);

            return StringUtils.equals((String) firstField.get(o), (String) secondField.get(o));

        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}