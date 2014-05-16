package org.opencrash.api.implementation;

import org.opencrash.api.ObtainedExceptionService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.Obtained_exception;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 12.05.14.
 */
public class ObtainedExceptionServiceImpl implements ObtainedExceptionService {
    Logger logger = Logger.getLogger(ObtainedExceptionService.class.getName());

    public void newObtainedException(Obtained_exception obtainedException){
        try{
            HibernateDAO.getInstance().DAOObtainedException().add(obtainedException);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }
    public List<Object> getExceptionByApplication(Integer app_id){
        List<Object> obtained_exceptions = null;
        try {
            obtained_exceptions =HibernateDAO.getInstance().DAOObtainedException().loadTopByApplicationId(app_id);

        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return obtained_exceptions;
    }

    public List<Obtained_exception> getExceptionsByAppIdAndExId(Integer app_id,Integer exc_id,Integer offset){
        List<Obtained_exception> obtained_exceptions = null;
        try {
            obtained_exceptions =HibernateDAO.getInstance().DAOObtainedException().loadByIdAndAppId(app_id,exc_id,offset);

        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return obtained_exceptions;
    }
}
