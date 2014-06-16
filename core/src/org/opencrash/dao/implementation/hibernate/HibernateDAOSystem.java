package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOSystem;
import org.opencrash.domain_objects.MobileSystem;
import org.opencrash.util.HibernateUtil;

import java.sql.SQLException;

/**
 * Created by Fong on 12.05.14.
 */
public class HibernateDAOSystem extends HibernateDAOIdentifiable<MobileSystem> implements DAOSystem {
    protected Class getInnerClass() {
        return MobileSystem.class;
    }
    public MobileSystem getByVersion(String version) throws SQLException{
        Session session = null;
        MobileSystem mobileSystem = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            mobileSystem = (MobileSystem) session.createCriteria(getInnerClass()).add(Restrictions.eq("version",version)).uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return mobileSystem;
    }
}
