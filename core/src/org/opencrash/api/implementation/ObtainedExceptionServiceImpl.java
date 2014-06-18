package org.opencrash.api.implementation;

import org.opencrash.api.ObtainedExceptionService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.ObtainedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 12.05.14.
 */
@Service("obtainedExceptionService")
public class ObtainedExceptionServiceImpl implements ObtainedExceptionService {
    Logger logger = Logger.getLogger(ObtainedExceptionService.class.getName());

    public void newObtainedException(ObtainedException obtainedException){
        try{
            HibernateDAO.getInstance().DAOObtainedException().add(obtainedException);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }
    public List<ObtainedException> getExceptionByApplication(Integer app_id){
        List<ObtainedException> obtained_exceptions = null;
        try {
            obtained_exceptions =HibernateDAO.getInstance().DAOObtainedException().loadTopByApplicationId(app_id);

        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return obtained_exceptions;
    }

    public List<ObtainedException> getExceptionsByAppIdAndExId(Integer app_id,Integer exc_id,Integer offset){
        List<ObtainedException> obtained_exceptions = null;
        try {
            obtained_exceptions =HibernateDAO.getInstance().DAOObtainedException().loadByIdAndAppId(app_id,exc_id,offset);

        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return obtained_exceptions;
    }
    public Integer getCount(Integer applicationId, Integer exception_id){
        Integer count= null;
        try {
            count = HibernateDAO.getInstance().DAOObtainedException().countAll(applicationId,exception_id);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return  count;
    }
}
