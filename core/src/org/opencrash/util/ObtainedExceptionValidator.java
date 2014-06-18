package org.opencrash.util;

import org.opencrash.domain_objects.*;

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
    private ExceptionClass exceptionClass;
    private String backtrace;
    private String message;
    private Application application;
    private ParserObject object;
    private Map<String, String> errors = new HashMap<String, String>();

    public ObtainedExceptionValidator(ExceptionClass exceptionClass, Application application,ParserObject obj) {
        this.exceptionClass = exceptionClass;
        this.application = application;
        this.backtrace = obj.getBacktrace();
        this.message = obj.getMessage();
        this.object = obj;
    }

    public boolean valid(){
        return valid;
    }
    public void validate(){
        validateExceptionClass();
        validateApplication();
        validateBacktrace();
        validateUser();
        validateMessage();

        if(errors.isEmpty())
            valid=true;
    }

    private boolean validateExceptionClass(){
        if(objectNotNull(this.exceptionClass)){
            if(classValidator(this.exceptionClass,"ExceptionClass"))
                return true;
            else {
                errors.put("exceptionClass","wrong object");
                return false;
            }
        }else{
            errors.put("exceptionClass","empty object");
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
        if(notNull(object.getUid())){
                return true;
        }else{
            errors.put("mobileUser","empty uid");
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
    public ObtainedException buildObject(){
        ObtainedException exception = new ObtainedException();
        if(valid){
            exception.setBacktrace(backtrace);
            exception.setUid(object.getUid());
            exception.setApplication(application);
            exception.setMessage(message);
            exception.setExceptionClass(exceptionClass);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            exception.setCreate_at(date);
            if(object.getScreenOrientation() !=null)
                exception.setScreen_orientation(object.getScreenOrientation());
            if(object.getGpsStatus() != null)
                exception.setGps_on(object.getGpsStatus());
            if(object.getWifiStatus() != null)
                exception.setWifi_on(object.getWifiStatus());
            if(object.getScreenHeight() != null)
                exception.setScreen_height(object.getScreenHeight());
            if(object.getScreenWidth() != null)
                exception.setScreen_width(object.getScreenWidth());
            if(object.getOsVersion() != null)
                exception.setOsVersion(object.getOsVersion());
        }else{
            errors.put("obtained_exception","invalid data");
        }
        return exception;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
