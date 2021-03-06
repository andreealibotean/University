package com.Universities.web.Validator;

import com.Universities.web.Model.Professor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by andreealibotean on 11/9/2015.
 */
@Component
public class ProfessorValidator implements Validator {

    public boolean supports(Class clazz) {
        return Professor.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idfaculty", "required.idfaculty", "idfaculty is mandatory.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "name is mandatory.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "required.birthDate", "birthDate is mandatory.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "required.sex", "sex/gender is mandatory.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CNP", "required.CNP", "CNP is mandatory.");

    }
}
