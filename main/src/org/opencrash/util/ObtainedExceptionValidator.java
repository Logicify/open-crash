package org.opencrash.util;

import org.opencrash.domain_objects.Application;
import org.opencrash.domain_objects.User;
import org.opencrash.domain_objects.Exception_class;
import org.opencrash.domain_objects.Obtained_exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 13.05.14.
 */
public class ObtainedExceptionValidator extends Validator {
    private boolean valid = false;
    private Exception_class exception_class;
    private String backtrace;
    private User applicationuser;
    private String message;
    private Application application;
    private Map<String, String> errors = new HashMap<String, String>();

    public ObtainedExceptionValidator(Exception_class exception_class, String backtrace, User applicationuser, String message, Application application) {
        this.exception_class = exception_class;
        this.backtrace = backtrace;
        this.applicationuser = applicationuser;
        this.message = message;
        this.application = application;
    }

    public boolean valid(){
        return valid;
    }
    public void validate(){
        if(errors.isEmpty()&&
                          validateExceptionClass()&&
                          validateApplication()&&
                          validateBacktrace()&&
                          validateUser()&&
                          validateMessage()
                          )
            valid=true;
    }
    private boolean validateExceptionClass(){
        if(objectNotNull(this.exception_class)){
            if(classValidator(this.exception_class,"Exception_class"))
                return true;
            else {
                errors.put("exception_class","wrong object");
                return false;
            }
        }else{
            errors.put("exception_class","empty object");
            return false;
        }
    }
    private boolean validateBacktrace(){
        if(notNull(this.backtrace))
            return true;
        else {
            errors.put("backtrace","backtrace is empty");
            return false;
        }
    }

    private boolean validateUser(){
        if(objectNotNull(this.applicationuser)){
            if(classValidator(this.applicationuser,"User"))
                return true;
            else {
                errors.put("applicationuser","wrong object");
                return false;
            }
        }else{
            errors.put("applicationuser","empty object");
            return false;
        }
    }

    private boolean validateApplication(){
        if(objectNotNull(this.application)){
            if(classValidator(this.application,"Application"))
                return true;
            else {
                errors.put("application","wrong object");
                return false;
            }
        }else{
            errors.put("application","empty object");
            return false;
        }
    }

    private boolean validateMessage(){
        if(notNull(this.message))
            return true;
        else {
            errors.put("message","message is empty");
            return false;

        }
    }
    public Obtained_exception buildObject(){
        Obtained_exception obtained_exception = new Obtained_exception();
        if(valid){
            obtained_exception.setUser(applicationuser);
            obtained_exception.setBacktrace(backtrace);
            obtained_exception.setApplication(application);
            obtained_exception.setMessage(message);
            obtained_exception.setException_class(exception_class);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            obtained_exception.setCreate_at(date);
        }else{
            errors.put("obtained_exception","invalid data");
        }
        return obtained_exception;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
