package org.opencrash.api.implementation;

import org.opencrash.api.ApplicationUserService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.User;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 12.05.14.
 */
public class ApplicationUserServiceImpl implements ApplicationUserService {
    Logger logger = Logger.getLogger(ApplicationUserService.class.getName());
    public User getUser(String uid){
        User applicationuser =null;
        try {
            applicationuser = HibernateDAO.getInstance().DAOApplicationUser().getByUid(uid);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return applicationuser;
    }

    public void newUser(User applicationuser){
        try{
            HibernateDAO.getInstance().DAOApplicationUser().add(applicationuser);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }
}
