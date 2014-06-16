package org.opencrash.dao;

import org.opencrash.dao.implementation.hibernate.*;

public class HibernateDAO {
    private HibernateDAOExceptionClass EXCEPTION_CLASS = null;
    private HibernateDAOApplication APPLICATION = null;
    private HibernateDAOObtainedException OBTAINED_EXCEPTION = null;
    private HibernateDAOUser DAO_User = null;
    private HibernateDAOSystem DAO_SYSTEM = null;
    private static HibernateDAO INSTANCE = null;

    public static HibernateDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HibernateDAO();
        }
        return INSTANCE;
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
    public HibernateDAOSystem DAOSystem(){
        if(DAO_SYSTEM == null){
            DAO_SYSTEM = new HibernateDAOSystem();
        }
        return DAO_SYSTEM;
    }
}
