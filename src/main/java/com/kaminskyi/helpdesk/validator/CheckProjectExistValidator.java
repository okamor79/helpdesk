package com.kaminskyi.helpdesk.validator;

import com.kaminskyi.helpdesk.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckProjectExistValidator implements ConstraintValidator<CheckProjectExist, String> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (projectRepository.findProjectByCode(s.toLowerCase()) == null) {
            return true;
        }
        return false;
    }
}
