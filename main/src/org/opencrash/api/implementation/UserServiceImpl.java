package org.opencrash.api.implementation;

import org.opencrash.api.UserService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.Register_user;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 14.05.14.
 */
public class UserServiceImpl implements UserService {
    Logger logger = Logger.getLogger(UserService.class.getName());
    public boolean checkEmail(String email){
        Register_user registerUser =null;
        try {
            registerUser = HibernateDAO.getInstance().DAOUser().getByEmail(email);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        if(registerUser == null)
            return true;
        else
            return false;
    }
    public void addUser(Register_user registerUser){
        try{
            HibernateDAO.getInstance().DAOUser().add(registerUser);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }

    public Register_user getUser(String email, String password){
        Register_user user =null;
        try{
            user = HibernateDAO.getInstance().DAOUser().getForLogin(email,password);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return user;
    }
    public Register_user getByid(Integer user_id){
        Register_user user =null;
        try {
            user = HibernateDAO.getInstance().DAOUser().getById(user_id);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return user;
    }
}
