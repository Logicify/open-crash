package org.opencrash.util;

import org.opencrash.domain_objects.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 13.05.14.
 */
public class ApplicationValidator extends Validator {
    private boolean valid=false;
    private String name;
    private String version;
    Map<String, String> errors = new HashMap<String, String>();

    public ApplicationValidator(String name,String version) {
        this.name = name;
        this.version = version;
    }

    public void validate(){
        if(validName()&&validVersion())
            valid = true;
    }

    public boolean valid(){
        return valid;
    }

    private boolean validName(){
        if(notNull(this.name)){
            if(maxLength(this.name,25)){
                return true;
            }else{
                errors.put("application_name", "incorrect name");
                return false;
            }
        }else{
            errors.put("application_name", "empty string");
            return false;
        }

    }
    private boolean validVersion(){
        if(notNull(this.version)){
            if(maxLength(this.version,10)){
                return true;
            }else{
                errors.put("application_version", "incorrect version");
                return false;
            }
        }else{
            errors.put("application_version", "empty string");
            return false;
        }

    }
    public Application buildObject(){
        Application application = new Application();
        if(valid){
            application.setName(name);
            application.setVersion(version);
        }else{
            errors.put("application","invalid data");
        }
        return application;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
