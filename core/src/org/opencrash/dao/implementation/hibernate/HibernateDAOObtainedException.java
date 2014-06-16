package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.opencrash.dao.interfaces.DAOObtainedException;
import org.opencrash.domain_objects.ObtainedException;
import org.opencrash.util.HibernateUtil;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public class HibernateDAOObtainedException extends HibernateDAOIdentifiable<ObtainedException> implements DAOObtainedException{
    protected Class getInnerClass() {
        return ObtainedException.class;
    }

    public List<Object> loadTopByApplicationId(int app_id){
        List<Object> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //obtained_exceptions = session.createQuery("SELECT exception_class_id,COUNT(*) FROM obtained_exceptions Where application_id =1 GROUP BY exception_class_id").list();
            //obtained_exceptions = session.createCriteria(ObtainedException.class).createAlias("application", "application").createAlias("exception_class","exception_class").add(Restrictions.eq("application.id", app_id)).setProjection(Projections.groupProperty("exception_class.exception_class")).list();
            obtained_exceptions = session.createSQLQuery("Select exception_class_id,COUNT(*),ex.exception_class from obtained_exceptions Join exception_classes as ex on ex.id = exception_class_id Group by exception_class_id,exception_class").list();
        } catch (Exception e) {
            // throw new SQLException("Data error", e)
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtained_exceptions;
    }

    public List<ObtainedException> loadByIdAndAppId(Integer app_id,Integer exc_id,Integer offset){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            obtained_exceptions= session.createCriteria(getInnerClass()).createAlias("application","application")
                                                               .list();
        } catch (Exception e) {
            // throw new SQLException("Data error", e)
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtained_exceptions;
    }
}
