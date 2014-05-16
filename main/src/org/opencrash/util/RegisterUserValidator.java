package org.opencrash.util;

import org.opencrash.api.UserService;
import org.opencrash.api.implementation.UserServiceImpl;
import org.opencrash.domain_objects.Register_user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 14.05.14.
 */
public class RegisterUserValidator extends Validator {
    String username;
    String password;
    String check_password;
    boolean valid = false;
    String email; Map<String, String> errors = new HashMap<String, String>();


    public RegisterUserValidator(String username, String password,String check_password, String email) {
        this.username = username;
        this.password = password;
        this.check_password = check_password;
        this.email = email;
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
            errors.put("username","username is empty");
            return false;
        }
    }
    private boolean validateEmail(){
        if(notNull(this.email)){
            if(isEmail(this.email)){
                if(notUse(this.email)){
                    return true;
                }else{
                    errors.put("e-mail","Email is already used!");
                    return false;
                }
            }else{
                errors.put("e-mail","incorrect email");
                return false;
            }
        }else{
            errors.put("e-mail","email  is empty");
            return false;
        }
    }

    private boolean validateEmailForLogin(){
        if(notNull(this.email)){
            if(isEmail(this.email)){
                return  true;
            }else{
                errors.put("e-mail","incorrect email");
                return false;
            }
        }else{
            errors.put("e-mail","email  is empty");
            return false;
        }
    }
    private boolean notUse(String email) {
        UserService userService = new UserServiceImpl();
        return userService.checkEmail(email);
    }

    private boolean validatePassword(){
        if(this.password.equals(this.check_password)){
            if(notNull(this.password)){
                return true;
            }else{
                errors.put("password","You must enter your password");
                return false;
            }
        }else{
            errors.put("password","password doesn't match");
            return false;
        }
    }

    public void validate(){
        if(validateUsername()&&validateEmail()&&validatePassword())
            valid = true;
    }

    public Register_user objectBuilder(){
        Register_user registerUser = new Register_user();
        if(valid){
            registerUser.setUsername(username);
            registerUser.setEmail(email);
            Security security = new Security();
            registerUser.setPassword(security.getHashPassword(password));
        }
        return registerUser;
    }
    public boolean isValid(){
        return valid;
    }

    public void validateForLogin() {
        if(validateEmailForLogin()&&validatePassword())
            valid = true;
    }

}
