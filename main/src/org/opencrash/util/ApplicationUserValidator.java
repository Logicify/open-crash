package org.opencrash.util;

import org.opencrash.domain_objects.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 13.05.14.
 */
public class ApplicationUserValidator extends Validator {
    private boolean valid=false;
    private String Uid;
    Map<String, String> errors = new HashMap<String, String>();

    public ApplicationUserValidator(String uid) {
        Uid = uid;
    }

    public void validate(){
        if(validUid())
            valid = true;
    }

    public boolean valid(){
        return valid;
    }

    private boolean validUid(){
        if(notNull(this.Uid)){
            if(maxLength(this.Uid,25)){
                return true;
            }else{
                errors.put("uid", "incorrect uid");
                return false;
            }
        }else{
            errors.put("uid", "empty string");
            return false;
        }

    }
    public User buildObject(){
        User applicationuser = new User();
        if(valid){
            applicationuser.setUid(Uid);
        }else{
            errors.put("applicationuser","invalid data");
        }
        return applicationuser;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
