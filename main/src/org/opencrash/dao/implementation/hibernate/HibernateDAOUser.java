package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOUser;
import org.opencrash.domain_objects.Register_user;
import org.opencrash.util.HibernateUtil;

public class HibernateDAOUser extends HibernateDAOIdentifiable<Register_user> implements DAOUser {

    protected Class getInnerClass() {
        return Register_user.class;
    }
    public Register_user getByEmail(String email){
        Session session = null;
        Register_user registerUser = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            registerUser = (Register_user) session.createCriteria(Register_user.class).add(Restrictions.eq("email", email)).uniqueResult();
        } catch (Exception e) {
           // throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return registerUser;
    }
    public Register_user getForLogin(String email, String password){
        Session session = null;
        Register_user registerUser = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            registerUser = (Register_user) session.createCriteria(Register_user.class).add(Restrictions.eq("email", email)).add(Restrictions.eq("password",password)).uniqueResult();
        } catch (Exception e) {
            // throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return registerUser;
    }
}
