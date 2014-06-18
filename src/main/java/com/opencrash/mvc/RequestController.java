package com.opencrash.mvc;


import org.opencrash.ExceptionParser;
import org.opencrash.api.ApplicationService;
import org.opencrash.api.HandlerService;
import org.opencrash.domain_objects.ApiResponseErrors;
import org.opencrash.domain_objects.ApiResponseObject;
import org.opencrash.domain_objects.Application;
import org.opencrash.domain_objects.ParserObject;
import org.opencrash.util.ApiExceptions;
import org.opencrash.util.ApiResponse;
import org.opencrash.util.exceptions.HandlerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 19.05.14.
 */
@Controller
public class RequestController {
    Logger logger = Logger.getLogger(RequestController.class.getName());

    @Autowired
    private  ApplicationService applicationService;
    @Autowired
    private HandlerService handlerService;

    @RequestMapping(value ="/send/exception/application/{application_key}",method = RequestMethod.POST)
    public @ResponseBody
    String getException(@PathVariable("application_key") String key,@RequestBody String exc,HttpServletResponse response){
        ApiResponseObject obj = new ApiResponseObject();
        Application application = applicationService.getByKey(key);
        Set<ApiResponseErrors> apiResponseErrorsSet = new HashSet<ApiResponseErrors>(0);
        if(application != null){
            try {
                ExceptionParser exceptionParser = new ExceptionParser(exc);
                ParserObject exception = exceptionParser.parse();
                try{
                    handlerService.handleException(application,exception);
                    obj.setSuccess(true);
                    response.setStatus(HttpServletResponse.SC_OK);
                }catch (HandlerServiceException e){
                    logger.log(Level.SEVERE,"Bad request data:",e);
                    apiResponseErrorsSet.add(new ApiResponseErrors(e.getErrorCode(), e.getMessage()));
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }catch (ApiExceptions e){
                apiResponseErrorsSet.add(new ApiResponseErrors(e.getErrorCode(),e.getMessage()));
                logger.log(Level.SEVERE, "Bad request data :", e);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }else {
            obj.setSuccess(false);
            apiResponseErrorsSet.add(new ApiResponseErrors(6, "Wrong access key"));
            logger.log(Level.SEVERE, "Bad request,wrong access key");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        obj.setErrors(apiResponseErrorsSet);
        ApiResponse apiResponse = new ApiResponse(obj);
        apiResponse.setResponse();
        return apiResponse.getResponseJson();
    }


}
