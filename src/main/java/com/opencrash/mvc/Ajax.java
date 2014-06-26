package com.opencrash.mvc;

import org.opencrash.api.AjaxService;
import org.opencrash.domain_objects.FilterObject;
import org.opencrash.domain_objects.ObtainedException;
import org.opencrash.util.ApiExceptions;
import org.opencrash.util.FiltersParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Fong on 23.05.14.
 */
@Controller
public class Ajax {
    @Autowired
    AjaxService ajaxService;
    @RequestMapping(value ="/filter" , method = RequestMethod.POST)
    public @ResponseBody
                String save(HttpServletRequest request) {
        //@RequestBody String jsonString
        String jsonString = request.getParameter("json");
        Integer page = Integer.parseInt(request.getParameter("page"));
        FiltersParser filtersParser = new FiltersParser(jsonString);
        List<ObtainedException> list =null;
        Integer total_elements = null;
        try {
            filtersParser.Parse();
            FilterObject filterObject = filtersParser.getFilters();
            Integer offset = (page-1) * 10;
            list = ajaxService.loadByFilters(filterObject,offset);
            total_elements = ajaxService.getTotalElements(filterObject);
        }catch (ApiExceptions e){
        }
        double total_pages = Math.ceil(total_elements/10);
        return filtersParser.getResult(list,(int) total_pages+1);
    }

}