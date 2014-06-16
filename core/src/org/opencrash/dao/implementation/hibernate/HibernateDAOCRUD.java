package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.opencrash.dao.interfaces.DAOCRUD;
import org.opencrash.domain_objects.BaseObject;
import org.opencrash.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


abstract class HibernateDAOCRUD<T extends BaseObject> implements DAOCRUD<T> {

    abstract protected Class getInnerClass();

    public void add(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public void update(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<T> getAll() throws SQLException {
        Session session = null;
        List<T> t = new ArrayList<T>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.createCriteria(this.getInnerClass()).list();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return t;
    }


    public void remove(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }






}
