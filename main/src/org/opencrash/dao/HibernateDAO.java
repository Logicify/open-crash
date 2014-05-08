package org.opencrash.dao;

import org.opencrash.dao.implementation.hibernate.HibernateDAOUser;

public class HibernateDAO {

    private HibernateDAOUser DAO_USER = null;
    private static HibernateDAO INSTANCE = null;

    public static HibernateDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HibernateDAO();
        }
        return INSTANCE;
    }

    public HibernateDAOUser DAOUser() {
        if (DAO_USER == null) {
            DAO_USER = new HibernateDAOUser();
        }
        return DAO_USER;
    }

}
