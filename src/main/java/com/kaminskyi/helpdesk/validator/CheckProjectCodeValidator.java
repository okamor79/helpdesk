package com.kaminskyi.helpdesk.validator;

import com.kaminskyi.helpdesk.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckProjectCodeValidator implements ConstraintValidator<CheckProjectCode, String> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!s.contains("-")) {
            return true;
        }
        return false;
    }


}
