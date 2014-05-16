package org.opencrash.dao;

import org.opencrash.dao.implementation.hibernate.*;

public class HibernateDAO {

    private HibernateDAOApplicationUsers DAO_APPLICATION_USERS = null;
    private HibernateDAOExceptionClass EXCEPTION_CLASS = null;
    private HibernateDAOApplication APPLICATION = null;
    private HibernateDAOObtainedException OBTAINED_EXCEPTION = null;
    private HibernateDAOUser DAO_User = null;
    private static HibernateDAO INSTANCE = null;

    public static HibernateDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HibernateDAO();
        }
        return INSTANCE;
    }

    public HibernateDAOApplicationUsers DAOApplicationUser() {
        if (DAO_APPLICATION_USERS == null) {
            DAO_APPLICATION_USERS = new HibernateDAOApplicationUsers();
        }
        return DAO_APPLICATION_USERS;
    }
    public HibernateDAOExceptionClass DAOExceptionClass(){
        if(EXCEPTION_CLASS == null){
            EXCEPTION_CLASS = new HibernateDAOExceptionClass();
        }
        return EXCEPTION_CLASS;
    }
    public HibernateDAOApplication DAOApplication(){
        if(APPLICATION == null){
            APPLICATION = new HibernateDAOApplication();
        }
        return APPLICATION;
    }

    public HibernateDAOObtainedException DAOObtainedException(){
        if(OBTAINED_EXCEPTION == null){
            OBTAINED_EXCEPTION = new HibernateDAOObtainedException();
        }
        return OBTAINED_EXCEPTION;
    }

    public HibernateDAOUser DAOUser(){
        if(DAO_User == null){
            DAO_User = new HibernateDAOUser();
        }
        return DAO_User;
    }
}
