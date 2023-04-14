package pl.coderslab.finalproject.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.sql.Date;


//@Target({ElementType.FIELD, ElementType.METHOD}) spr- czy to nie wystarczy
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = DateAfterValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateAfter {
    String message() default "Data początkowa musi być przed datą końcową   ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    //czemu nie może być date?
    String startDate();
    String endDate();

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)

    public @interface List {
        DateAfter[] value();
    }


}
