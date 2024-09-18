package org.example.game_tournement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<ValidUser, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Remplis fréro")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        if (value.length() < 2) {
            context.buildConstraintViolationWithTemplate("Une lettre ? Vraiment ?")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        return true;
    }
}