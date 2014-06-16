package com.opencrash.mvc;

import org.opencrash.api.ObtainedExceptionService;
import org.opencrash.api.implementation.ObtainedExceptionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Fong on 23.05.14.
 */
@Controller
public class Ajax {
    @RequestMapping(value = "/getApplicationInfo",method = RequestMethod.GET, headers="Accept=application/json")
    public  @ResponseBody
    List<Object> getTags(@RequestParam("app_id") int app_id) {
        ObtainedExceptionService obtainedExceptionService = new ObtainedExceptionServiceImpl();
       return obtainedExceptionService.getExceptionByApplication(app_id);
    }
}
