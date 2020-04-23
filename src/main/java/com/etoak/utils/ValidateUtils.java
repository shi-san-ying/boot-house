package com.etoak.utils;

import com.etoak.exception.ParamException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

public class ValidateUtils {
    private static Validator validator;
    static{
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        validator=factory.getValidator();
    }

    public static void validate(Object object){
        Set<ConstraintViolation<Object>> violations=validator.validate(object);
        Iterator<ConstraintViolation<Object>> iterator=violations.iterator();
        StringBuffer buffer =new StringBuffer();
        while(iterator.hasNext()){
            ConstraintViolation<Object> violation=iterator.next();
            String message=violation.getMessage();
            buffer.append(message);
        }
        throw  new ParamException("参数错误： "+buffer.toString());
    }
}
