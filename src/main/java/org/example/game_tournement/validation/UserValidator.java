package org.example.game_tournement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.game_tournement.Entity.User;

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

    public boolean isValid(User user, ConstraintValidatorContext context) {
        return user.getEmail() != null && user.getEmail().equals(user.getRepeatedEmail());
    }

}