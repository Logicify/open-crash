package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOApplicationUsers;
import org.opencrash.domain_objects.User;
import org.opencrash.util.HibernateUtil;

public class HibernateDAOApplicationUsers extends HibernateDAOIdentifiable<User> implements DAOApplicationUsers {

    protected Class getInnerClass() {
        return User.class;
    }
    public User getByUid(String uid){
        Session session = null;
        User applicationuser = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            applicationuser = (User) session.createCriteria(User.class).add(Restrictions.eq("uid", uid)).uniqueResult();
        } catch (Exception e) {
           // throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return applicationuser;
    }
}
