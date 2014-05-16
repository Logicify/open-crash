package org.opencrash.util;

import org.opencrash.domain_objects.Exception_class;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 13.05.14.
 */
public class ExceptionClassValidator extends Validator {
    private boolean valid=false;
    private String exception_class_name;
    Map<String, String> errors = new HashMap<String, String>();

    public ExceptionClassValidator(String exception_class_name) {
        this.exception_class_name = exception_class_name;
    }

    public void validate(){
        if(validExceptionClass())
            valid = true;
    }

    public boolean valid(){
        return valid;
    }

    private boolean validExceptionClass(){
        if(notNull(this.exception_class_name)){
            if(maxLength(this.exception_class_name,55)){
                return true;
            }else{
                errors.put("exception_class_name", "incorrect exception_class_name");
                return false;
            }
        }else{
            errors.put("exception_class_name", "empty string");
            return false;
        }

    }
    public Exception_class buildObject(){
        Exception_class exception_class = new Exception_class();
        if(valid){
            exception_class.setException_class(exception_class_name);
        }else{
            errors.put("exception_class_name","invalid data");
        }
        return exception_class;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
