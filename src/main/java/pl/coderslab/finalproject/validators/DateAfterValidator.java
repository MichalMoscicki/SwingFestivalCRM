package pl.coderslab.finalproject.validators;

import pl.coderslab.finalproject.models.festival.Festival;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateAfterValidator implements ConstraintValidator<StartDateBeforeEndDate, Festival> {



    @Override
    public void initialize(StartDateBeforeEndDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Festival festival, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate startDate = festival.getStartDate();
        LocalDate endDate = festival.getEndDate();
        if (startDate == null || endDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }
}
