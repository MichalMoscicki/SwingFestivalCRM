package pl.coderslab.finalproject.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.sql.Date;

public class DateAfterValidator implements ConstraintValidator <DateAfter, Date> {

    private Date date;

    @Override
    public void initialize(DateAfter constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
      //  this.date = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        if ( date == null ) {
            return true;
        }
        return false;
       // return date.after("anotherDate");
    }
}
