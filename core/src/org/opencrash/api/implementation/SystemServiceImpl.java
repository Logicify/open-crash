package org.opencrash.api.implementation;

import org.opencrash.api.SystemService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.MobileSystem;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

}
