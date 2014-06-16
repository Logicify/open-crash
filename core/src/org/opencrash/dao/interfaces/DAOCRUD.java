package org.opencrash.dao.interfaces;

import org.opencrash.domain_objects.BaseObject;

import java.sql.SQLException;
import java.util.List;

public interface DAOCRUD<T extends BaseObject> {
    public void add(T obj) throws SQLException;

    public void update(T obj) throws SQLException;

    //public T getById(int id) throws SQLException;

    public List<T> getAll() throws SQLException;

    //public void remove(int id) throws SQLException;
    public void remove(T t) throws SQLException;

}
