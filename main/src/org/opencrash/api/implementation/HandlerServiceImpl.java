package org.opencrash.api.implementation;

import org.opencrash.api.ApplicationUserService;
import org.opencrash.api.ExceptionClassService;
import org.opencrash.api.HandlerService;
import org.opencrash.api.ObtainedExceptionService;
import org.opencrash.domain_objects.Application;
import org.opencrash.domain_objects.Exception_class;
import org.opencrash.domain_objects.Obtained_exception;
import org.opencrash.domain_objects.User;
import org.opencrash.util.ApplicationUserValidator;
import org.opencrash.util.ExceptionClassValidator;
import org.opencrash.util.ObtainedExceptionValidator;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 13.05.14.
 */
public class HandlerServiceImpl implements HandlerService {
    Logger logger = Logger.getLogger(HandlerService.class.getName());

    public void handleException(String exception_class_name,String exception_massage,String exception_backtrace,Application application,String uid){
        ApplicationUserService applicationUserService = new ApplicationUserServiceImpl();
        User applicationuser = applicationUserService.getUser(uid);
        ExceptionClassService exceptionClassService = new ExceptionClassServiceImpl();
        Exception_class exception_class = exceptionClassService.getExceptionClass(exception_class_name);

        if(applicationuser ==null){
            ApplicationUserValidator applicationUserValidator =new ApplicationUserValidator(uid);
            applicationUserValidator.validate();

            if(applicationUserValidator.valid()){
                applicationuser = applicationUserValidator.buildObject();
                applicationUserService.newUser(applicationuser);
                applicationuser = applicationUserService.getUser(uid);
            }else{
                Map<String,String> errors = applicationUserValidator.getErrors();
                for (String key : errors.keySet()) {
                    String value = errors.get(key);
                    logger.log(Level.SEVERE,key,value);
                }
            }
        }
        if(exception_class == null){
            ExceptionClassValidator exceptionClassValidator = new ExceptionClassValidator(exception_class_name);
            exceptionClassValidator.validate();
            if(exceptionClassValidator.valid()){
                exception_class = exceptionClassValidator.buildObject();
                exceptionClassService.AddNewClass(exception_class);
                exception_class = exceptionClassService.getExceptionClass(exception_class_name);
            }else{
                Map<String,String> errors = exceptionClassValidator.getErrors();
                for (String key : errors.keySet()) {
                    String value = errors.get(key);
                    logger.log(Level.SEVERE,key,value);
                }
            }

        }
        ObtainedExceptionValidator obtainedExceptionValidator = new ObtainedExceptionValidator(exception_class,exception_backtrace, applicationuser,exception_massage,application);
        obtainedExceptionValidator.validate();
        if(obtainedExceptionValidator.valid()){
            ObtainedExceptionService obtainedExceptionService = new ObtainedExceptionServiceImpl();
            Obtained_exception obtained_exception = obtainedExceptionValidator.buildObject();
            obtainedExceptionService.newObtainedException(obtained_exception);
        }else{
            Map<String,String> errors = obtainedExceptionValidator.getErrors();
            for (String key : errors.keySet()) {
                String value = errors.get(key);
                logger.log(Level.SEVERE,key,value);
            }
        }
    }
}
