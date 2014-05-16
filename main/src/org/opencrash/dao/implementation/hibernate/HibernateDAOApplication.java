package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOApplication;
import org.opencrash.domain_objects.Application;
import org.opencrash.util.HibernateUtil;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public class HibernateDAOApplication extends HibernateDAOIdentifiable<Application> implements DAOApplication {

    protected Class getInnerClass() {
        return Application.class;
    }
    public Application getByName(String app_name,String app_ver){
        Session session = null;
        Application application = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            application = (Application) session.createCriteria(Application.class).add(Restrictions.eq("name",app_name)).add(Restrictions.eq("version",app_ver)).uniqueResult();
        } catch (Exception e) {
            // throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return application;
    }
    public List<Application> loadByUserId(int user_id){
        Session session = null;
        List<Application> application = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            application = session.createCriteria(Application.class).createAlias("register_user","register_user").add(Restrictions.eq("register_user.id",user_id)).list();
        } catch (Exception e) {
            // throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return application;
    }
}
