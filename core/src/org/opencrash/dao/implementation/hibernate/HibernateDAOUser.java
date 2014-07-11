package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOCRUD;
import org.opencrash.domain_objects.Register_user;
import org.opencrash.util.HibernateUtil;

import java.sql.SQLException;

public class HibernateDAOUser extends HibernateDAOIdentifiable<Register_user> implements DAOCRUD<Register_user> {

    protected Class getInnerClass() {
        return Register_user.class;
    }
    public Register_user getByEmail(String email)throws SQLException{
        Session session = null;
        Register_user registerUser = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            registerUser = (Register_user) session.createCriteria(Register_user.class).add(Restrictions.eq("email", email)).uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return registerUser;
    }
    public Register_user getForLogin(String email, String password) throws SQLException{
        Session session = null;
        Register_user registerUser = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            registerUser = (Register_user) session.createCriteria(Register_user.class).add(Restrictions.eq("email", email)).add(Restrictions.eq("password",password)).uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return registerUser;
    }

    public Register_user getAdminForLogin(String username, String password)throws SQLException{
        Session session = null;
        Register_user admin = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            admin = (Register_user) session.createCriteria(getInnerClass())
                                           .add(Restrictions.eq("username", username))
                                           .add(Restrictions.eq("password",password))
                                           .add(Restrictions.eq("admin",true))
                                           .uniqueResult();
        } catch (Exception e) {
             throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return admin;
    }
}
