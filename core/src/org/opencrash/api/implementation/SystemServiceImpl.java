package org.opencrash.api.implementation;

import org.opencrash.api.SystemService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.MobileSystem;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 30.05.14.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService{
    Logger logger = Logger.getLogger(SystemService.class.getName());

    public MobileSystem getByVersion(String version){
        MobileSystem mobileSystem = null;
        try {
            mobileSystem = HibernateDAO.getInstance().DAOSystem().getByVersion(version);
        }catch (SQLException e){
            logger.log(Level.SEVERE,"DB error",e);
        }
        return mobileSystem;
    }
    public void addNewSystem(MobileSystem mobileSystem){
        try {
            HibernateDAO.getInstance().DAOSystem().add(mobileSystem);
            }catch (SQLException e){
                logger.log(Level.SEVERE,"DB error",e);
        }
    }
    public MobileSystem getById(Integer system_id){
        MobileSystem mobileSystem = null;
        try {
            mobileSystem = HibernateDAO.getInstance().DAOSystem().getById(system_id);
        }catch (SQLException e){
            logger.log(Level.SEVERE,"DB error",e);
        }
        return mobileSystem;
    }

    public List<MobileSystem> loadAll(){
        List<MobileSystem> list = null;
        try{
            list = HibernateDAO.getInstance().DAOSystem().getAll();
        }catch (SQLException e){
            logger.log(Level.SEVERE,"DB error",e);
        }
        return list;
    }
    public void saveEdit(MobileSystem system){
        try{
            HibernateDAO.getInstance().DAOSystem().update(system);
        }catch (SQLException e){
            logger.log(Level.SEVERE,"DB error",e);
        }
    }
    public void deleteSystem(MobileSystem system){
        try{
            HibernateDAO.getInstance().DAOSystem().remove(system);
        }catch (SQLException e){
            logger.log(Level.SEVERE,"DB error",e);
        }
    }
}
