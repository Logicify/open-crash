package org.opencrash.api.implementation;

import org.opencrash.api.AjaxService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.FilterObject;
import org.opencrash.domain_objects.ObtainedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 20.06.14.
 */
@Service("ajaxService")
public class AjaxServiceImpl implements AjaxService {
    Logger logger = Logger.getLogger(AjaxService.class.getName());

    public List<ObtainedException> loadByFilters( FilterObject obj,Integer offset,String sorting_field,String sorting_type,Integer limit){
        List<ObtainedException> obtainedExceptions=null;
        try {
            obtainedExceptions = HibernateDAO.getInstance().DAOObtainedException().loadByFilters(obj,offset,sorting_field,sorting_type,limit);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return obtainedExceptions;
    }
    public Integer getTotalElements(FilterObject filterObject){
        Integer total = null;
        try {
            total = HibernateDAO.getInstance().DAOObtainedException().getTotalElements(filterObject);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return total;
    }
}
