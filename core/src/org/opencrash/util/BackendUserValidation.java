package org.opencrash.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 11.07.14.
 */
public class BackendUserValidation extends Validator {
    private String username;
    private String password;
    private boolean valid = false;
    private boolean name_check = true;
    private boolean pass_check = true;
    private Map<String, String> errors = new HashMap<String, String>();

    public BackendUserValidation(String username, String password) {
        this.username = username;
        this.password = password;
    }
    private boolean validateUsername(){
        if(notNull(this.username)){
            if(maxLength(this.username,25)){
                return true;
            }else{
                errors.put("username","incorrect length");
                return false;
            }
        }else{
            errors.put("username","Field 'username' is empty");
            return false;
        }
    }

    private boolean validatePassword(){
        if(notNull(this.password)){
                if(checkPasswordType(this.password))
                    return true;
                else{
                    errors.put("password","Password must contains:one digital from 0-9,one lowercase characters,one uppercase characters,one special symbols in the list \"@#$%\",length at least 6 characters and maximum of 20");
                    return false;
                }
        }
        else{
            errors.put("password","You must enter your password");
            return false;
        }
    }
    public void validate(){
        validateUsername();;
        validatePassword();
        if(errors.isEmpty())
            valid = true;
    }

    public boolean isValid() {
        return valid;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
