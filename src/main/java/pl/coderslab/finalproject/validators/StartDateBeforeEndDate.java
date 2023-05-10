package pl.coderslab.finalproject.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target(ElementType.TYPE_USE)
@Constraint(validatedBy = DateAfterValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface StartDateBeforeEndDate {

    String message() default "Data początkowa musi być przed datą końcowa";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

@Target({ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        StartDateBeforeEndDate[] value();
    }

}
