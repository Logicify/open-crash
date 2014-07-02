package org.opencrash.api.implementation;

import org.opencrash.api.DeviceService;
import org.opencrash.api.ExceptionClassService;
import org.opencrash.api.HandlerService;
import org.opencrash.api.ObtainedExceptionService;
import org.opencrash.domain_objects.*;
import org.opencrash.util.DeviceValidator;
import org.opencrash.util.ExceptionClassValidator;
import org.opencrash.util.ObtainedExceptionValidator;
import org.opencrash.util.exceptions.HandlerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 13.05.14.
 */
@Service("handlerService")
public class HandlerServiceImpl implements HandlerService {
    Logger logger = Logger.getLogger(HandlerService.class.getName());
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private ExceptionClassService exceptionClassService;
    @Autowired
    private ObtainedExceptionService obtainedExceptionService;

    public void handleException(Application application,ParserObject obj) throws HandlerServiceException {
        ExceptionClass exceptionClass =null;
        try {
            exceptionClass = exceptionClassService.getExceptionClass(obj.getExceptionClass());
            if(exceptionClass == null){
                ExceptionClassValidator validator = new ExceptionClassValidator(obj.getExceptionClass());
                validator.validate();
                if(validator.valid()){
                    exceptionClass = validator.buildObject();
                    exceptionClassService.AddNewClass(exceptionClass);
                }else{
                    Map<String,String> errors = validator.getErrors();
                    String error="";
                    for (String key : errors.keySet()) {
                        String value = errors.get(key);
                        logger.log(Level.SEVERE,key,value);
                        error+=key+"-"+value+". ";
                    }
                    throw new HandlerServiceException("Cannot add new class.Validation errors:"+error,2);
                }

            }
            Device device = deviceService.getByModel(obj.getDevice());
            if(device == null){
                DeviceValidator deviceValidator = new DeviceValidator(obj.getDevice());
                deviceValidator.validate();
                if(deviceValidator.isValid()){
                    device = deviceValidator.buildObject();
                    deviceService.addNew(device);
                }
            }
            ObtainedExceptionValidator validator = new ObtainedExceptionValidator(exceptionClass,application,obj);
            validator.validate();
            if(validator.valid()){
                ObtainedException exception = validator.buildObject();
                exception.setDevice(device);
                obtainedExceptionService.newObtainedException(exception);
            }else{
                Map<String,String> errors = validator.getErrors();
                String error="";
                for (String key : errors.keySet()) {
                    String value = errors.get(key);
                    logger.log(Level.SEVERE,key,value);
                    error+=key+"-"+value+". ";
                }
                throw new HandlerServiceException("Cannot add new class.Validation errors:"+error,3);
            }
        }catch (HandlerServiceException e){
            throw new HandlerServiceException("Data error."+e.getMessage(),e.getErrorCode());
        }
    }
}
