package com.base64.gamesback.common.exception_handler.validation.validator;

import com.base64.gamesback.auth.user.service.UserService;
import com.base64.gamesback.common.exception_handler.validation.anotation.UserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserName implements ConstraintValidator<UserName, String> {

    private final UserService userService;

    public ValidUserName(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UserName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return !userService.existUserByName(userName);
    }
}
