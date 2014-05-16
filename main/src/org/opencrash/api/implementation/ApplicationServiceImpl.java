package org.opencrash.api.implementation;

import org.opencrash.api.ApplicationService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.Application;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 12.05.14.
 */
public class ApplicationServiceImpl implements ApplicationService {
    Logger logger = Logger.getLogger(ApplicationService.class.getName());

    public Application getApplication(String app_name,String app_ver){
        Application application=null;
        try {
            application = HibernateDAO.getInstance().DAOApplication().getByName(app_name,app_ver);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return application;
    }

    public void newApplication(Application application){
        try {
            HibernateDAO.getInstance().DAOApplication().add(application);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }

    public List<Application> loadApplicationByUser(int user_id){
        List<Application> applications =null;
        try{
           applications =  HibernateDAO.getInstance().DAOApplication().loadByUserId(user_id);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return applications;
    }

    public Application getById(Integer id){
        Application application = null;
        try {
            application = HibernateDAO.getInstance().DAOApplication().getById(id);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return application;
    }


}
