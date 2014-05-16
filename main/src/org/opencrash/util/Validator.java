package org.opencrash.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by Fong on 13.05.14.
 */
public class Validator {

    public boolean notNull(String str){
        if(str.isEmpty())
            return false;
        else
            return true;
    }

    public boolean maxLength(String str,int length){
        if(str.length()<=length)
            return true;
        else
            return false;
    }

    public boolean classValidator(Object obj,String className){
        if(obj.getClass().getSimpleName().equals(className))
            return true;
        else
            return false;
    }
    public boolean objectNotNull(Object obj){
        if(obj != null)
            return true;
        else
            return false;
    }

    public boolean isEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;

    }
}
