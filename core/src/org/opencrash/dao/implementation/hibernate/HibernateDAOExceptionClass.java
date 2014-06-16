package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOExceptionClass;
import org.opencrash.domain_objects.ExceptionClass;
import org.opencrash.util.HibernateUtil;

public class HibernateDAOExceptionClass extends HibernateDAOIdentifiable<ExceptionClass> implements DAOExceptionClass {

    protected Class getInnerClass() {
        return ExceptionClass.class;
    }

    public ExceptionClass getByClassName(String class_name){
        Session session = null;
        ExceptionClass exception_class = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
             exception_class= (ExceptionClass) session.createCriteria(ExceptionClass.class).add(Restrictions.eq("exception_class", class_name)).uniqueResult();
        } catch (Exception e) {
           // throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return exception_class;
    }


}
