package com.prmproject.hairsalonbooking.data.model.dataToSend;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndDateValidator.class)
public @interface EndDateValidation {
    String message() default "End date must be after start date.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String startDate();
}

