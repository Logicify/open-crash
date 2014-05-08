package org.opencrash.dao.implementation.hibernate;

import org.opencrash.dao.interfaces.DAOUser;
import org.opencrash.domain_objects.User;

public class HibernateDAOUser extends HibernateDAOIdentifiable<User> implements DAOUser {

    protected Class getInnerClass() {
        return User.class;
    }
}
