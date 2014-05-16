package org.opencrash.api.implementation;

import org.omg.DynamicAny._DynEnumStub;
import org.opencrash.api.ExceptionClassService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.Exception_class;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 12.05.14.
 */
public class ExceptionClassServiceImpl implements ExceptionClassService{

    Logger logger = Logger.getLogger(ExceptionClassService.class.getName());

    public Exception_class getExceptionClass(String exception_class_name){
        Exception_class exception_class=null;
        try {
            exception_class = HibernateDAO.getInstance().DAOExceptionClass().getByClassName(exception_class_name);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return exception_class;
    }

    public void AddNewClass(Exception_class newClass){
        try {
            HibernateDAO.getInstance().DAOExceptionClass().add(newClass);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }

}
