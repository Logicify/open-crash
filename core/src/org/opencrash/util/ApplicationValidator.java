package org.opencrash.util;

import org.opencrash.domain_objects.Application;
import org.opencrash.domain_objects.MobileSystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 13.05.14.
 */
public class ApplicationValidator extends Validator {
    private boolean valid=false;
    private String name;
    private String version;
    private MobileSystem system;
    Map<String, String> errors = new HashMap<String, String>();

    public ApplicationValidator(String name,String version,MobileSystem system) {
        this.name = name;
        this.version = version;
        this.system = system;
    }

    public void validate(){
        validName();
        validVersion();
        validSystem();
        if(errors.isEmpty())
            valid = true;
    }

    private boolean validSystem() {
        if(objectNotNull(this.system)){
                return true;
        }else {
            errors.put("system", "Wrong system");
            return false;
        }
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
            application.setMobileSystem(system);
        }else{
            errors.put("application","invalid data");
        }
        return application;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
