package org.opencrash.dao.interfaces;

import org.opencrash.domain_objects.IdentifiableEntity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;


public interface DAOIdentifiable<T extends IdentifiableEntity> extends DAOCRUD<T> {
    public T getById(int id) throws SQLException;

    public Set getByIds(Collection<Integer> ids) throws SQLException;

    public Set getByIds(Integer[] ids) throws SQLException;

    public void remove(int id) throws SQLException;
}
