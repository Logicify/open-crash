package com.opencrash.mvc;

import org.opencrash.api.AjaxService;
import org.opencrash.domain_objects.FilterObject;
import org.opencrash.domain_objects.ObtainedException;
import org.opencrash.util.ApiExceptions;
import org.opencrash.util.FiltersParser;
import org.opencrash.util.Settings;
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
        String jsonString = request.getParameter("json");
        Integer page = Integer.parseInt(request.getParameter("page"));
        String sorting_type = request.getParameter("sorting_type");
        String sorting_field = request.getParameter("sorting_field");
        FiltersParser filtersParser = new FiltersParser(jsonString);
        List<ObtainedException> list =null;
        Integer total_elements = null;
        boolean grouping = false;
        try {
            filtersParser.Parse();
            FilterObject filterObject = filtersParser.getFilters();
            Settings settings = new Settings();
            settings.getSettings();
            Integer limit = Integer.parseInt(settings.getPagination());
            Integer offset = (page-1) * limit;
            list = ajaxService.loadByFilters(filterObject,offset,filtersParser.getSortField(sorting_field),sorting_type,limit);
            total_elements = ajaxService.getTotalElements(filterObject);
            grouping = filterObject.isGrouping();
        }catch (ApiExceptions e){

        }
        int total=0;
        double total_pages = Math.ceil(total_elements/10);
        return filtersParser.getResult(list,(int)total_pages+1,grouping);
    }

}