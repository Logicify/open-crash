package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOObtainedException;
import org.opencrash.domain_objects.ObtainedException;
import org.opencrash.util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Fong on 12.05.14.
 */
public class HibernateDAOObtainedException extends HibernateDAOIdentifiable<ObtainedException> implements DAOObtainedException{
    protected Class getInnerClass() {
        return ObtainedException.class;
    }

    public List<ObtainedException> loadTopByApplicationId(int app_id){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //obtained_exceptions = session.createSQLQuery("Select exception_class_id,COUNT(*),ex.exception_class from obtained_exceptions Join exception_classes as ex on ex.id = exception_class_id WHERE application_id="+app_id+" Group by exception_class_id,exception_class").list();
            obtained_exceptions = session.createCriteria(getInnerClass())
                                         .createAlias("exceptionClass","exceptionClass")
                                         .createAlias("application","application")
                                         .add(Restrictions.eq("application.id",app_id))
                                         .setProjection( Projections.projectionList()
                                            .add( Projections.rowCount() )
                                            .add( Projections.groupProperty("exceptionClass.id"))
                                            .add( Projections.groupProperty("exceptionClass.exception_class"))
                                         )
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

    public List<ObtainedException> loadByIdAndAppId(Integer app_id,Integer exc_id,Integer offset){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            obtained_exceptions= session.createCriteria(getInnerClass())
                                        .createAlias("application","app")
                                        .add(Restrictions.eq("app.id",app_id))
                                        .createAlias("exceptionClass","exc")
                                        .add(Restrictions.eq("exc.id",exc_id))
                                        .setFirstResult(offset)
                                        .setMaxResults(10)
                                        .list();
        } catch (Exception e) {
            //
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtained_exceptions;
    }

    public Integer countAll(Integer applicationId, Integer exception_id) {
        Integer count = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            count= session.createCriteria(getInnerClass())
                    .createAlias("application","app")
                    .add(Restrictions.eq("app.id",applicationId))
                    .createAlias("exceptionClass","exc")
                    .add(Restrictions.eq("exc.id",exception_id))
                    .list().size();
        } catch (Exception e) {
            //
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }
}
