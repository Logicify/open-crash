package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOExceptionClass;
import org.opencrash.domain_objects.Exception_class;
import org.opencrash.util.HibernateUtil;

public class HibernateDAOExceptionClass extends HibernateDAOIdentifiable<Exception_class> implements DAOExceptionClass {

    protected Class getInnerClass() {
        return Exception_class.class;
    }

    public Exception_class getByClassName(String class_name){
        Session session = null;
        Exception_class exception_class = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
             exception_class= (Exception_class) session.createCriteria(Exception_class.class).add(Restrictions.eq("exception_class", class_name)).uniqueResult();
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
