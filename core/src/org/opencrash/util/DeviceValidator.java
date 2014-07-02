package org.opencrash.util;

import org.opencrash.domain_objects.Device;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 02.07.14.
 */
public class DeviceValidator extends Validator{
    private boolean valid=false;
    private String name;
    private  Map<String, String> errors = new HashMap<String, String>();

    public DeviceValidator(String name) {
        this.name = name;
    }

    public void validate(){
        validName();
        if(errors.isEmpty())
            valid = true;
    }

    private boolean validName(){
        if(notNull(this.name)){
            if(maxLength(this.name,25)){
                return true;
            }else{
                errors.put("model", "incorrect name");
                return false;
            }
        }else{
            errors.put("model", "empty string");
            return false;
        }

    }

    public boolean isValid() {
        return valid;
    }

    public Device buildObject(){
        Device model = new Device();
        if(valid){
            model.setName(name);
        }else{
            errors.put("model","invalid data");
        }
        return model;
    }
}
