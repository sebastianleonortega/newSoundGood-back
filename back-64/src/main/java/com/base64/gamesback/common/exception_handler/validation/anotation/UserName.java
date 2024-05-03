package com.base64.gamesback.common.exception_handler.validation.anotation;

import com.base64.gamesback.common.exception_handler.validation.validator.ValidUserName;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidUserName.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {
    String message() default "Ya existe un usuario con el nombre ingresado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
