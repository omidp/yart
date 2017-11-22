package com.yart.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.yart.app.domain.DatasourceEntity;

@Component
public class DatasourceValidator implements Validator
{

    @Override
    public boolean supports(Class<?> clazz)
    {
        return DatasourceEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
        DatasourceEntity de = (DatasourceEntity) target;
        if(de.getDsType() == null)
            errors.rejectValue("dsType", "dsType", new Object[]{"'dsType'"}, "dsType can't be empty");
    }

}
