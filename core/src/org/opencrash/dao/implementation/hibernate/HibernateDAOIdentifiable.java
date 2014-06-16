package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOIdentifiable;
import org.opencrash.domain_objects.IdentifiableEntity;
import org.opencrash.util.HibernateUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;


abstract class HibernateDAOIdentifiable<T extends IdentifiableEntity> extends HibernateDAOCRUD<T> implements DAOIdentifiable<T> {

    public T getById(int id) throws SQLException {
        Session session = null;
        T t = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = (T) session.get(this.getInnerClass(), id);
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return t;
    }

    public HashSet getByIds(Collection<Integer> ids) throws SQLException {
        Session session = null;
        HashSet<T> categories = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Category cats;
            categories = new HashSet<T>(session.createCriteria(this.getInnerClass()).add(Restrictions.in("id", ids)).list());

        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categories;
    }

    public HashSet getByIds(Integer[] ids) throws SQLException {
        Session session = null;
        HashSet<T> categories = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Category cats;
            categories = new HashSet<T>(session.createCriteria(this.getInnerClass()).add(Restrictions.in("id", ids)).list());

        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categories;
    }

    public void remove(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(this.getById(id));
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
