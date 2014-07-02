package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAODevice;
import org.opencrash.domain_objects.Device;
import org.opencrash.util.HibernateUtil;

/**
 * Created by Fong on 02.07.14.
 */
public class HibernateDAODevice extends HibernateDAOIdentifiable<Device> implements DAODevice {

    protected Class getInnerClass() {
        return Device.class;
    }
    public  Device getByModel(String model){
        Session session = null;
        Device device = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            device = (Device) session.createCriteria(getInnerClass()).add(Restrictions.eq("name",model)).uniqueResult();
        } catch (Exception e) {
            // throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return device;
    }

}
