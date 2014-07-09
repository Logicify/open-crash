package org.opencrash.api.implementation;

import org.opencrash.api.ExceptionClassService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.ExceptionClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 12.05.14.
 */
@Service("exceptionClassService")
public class ExceptionClassServiceImpl implements ExceptionClassService{

    Logger logger = Logger.getLogger(ExceptionClassService.class.getName());

    public ExceptionClass getExceptionClass(String exception_class_name){
        ExceptionClass exception_class=null;
        try {
            exception_class = HibernateDAO.getInstance().DAOExceptionClass().getByClassName(exception_class_name);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return exception_class;
    }

    public void AddNewClass(ExceptionClass newClass){
        try {
            HibernateDAO.getInstance().DAOExceptionClass().add(newClass);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }
    public List<ExceptionClass> loadAll(){
        List<ExceptionClass> exception_classes=null;
        try {
            exception_classes = HibernateDAO.getInstance().DAOExceptionClass().getAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return exception_classes;
    }

}
