package org.opencrash.dao.implementation.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.opencrash.dao.interfaces.DAOObtainedException;
import org.opencrash.domain_objects.FilterObject;
import org.opencrash.domain_objects.ObtainedException;
import org.opencrash.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public class HibernateDAOObtainedException extends HibernateDAOIdentifiable<ObtainedException> implements DAOObtainedException{
    protected Class getInnerClass() {
        return ObtainedException.class;
    }

    public List<ObtainedException> loadTopByApplicationId(int app_id,String field,String type,Integer offset,Integer limit){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria= session.createCriteria(getInnerClass())
                                         .createAlias("exceptionClass", "exceptionClass")
                                         .createAlias("application", "application")
                                         .add(Restrictions.eq("application.id", app_id))
                                         .setProjection(Projections.projectionList()
                                                 .add(Projections.rowCount(), "count")
                                                 .add(Projections.groupProperty("exceptionClass.id"), "class")
                                                 .add(Projections.groupProperty("exceptionClass.exception_class"))
                                         );
            if(field == null){
                criteria.addOrder(Order.desc("count"));
            }else {
                if(type.equals("desc"))
                    criteria.addOrder(Order.desc(field));
                else
                    criteria.addOrder(Order.asc(field));
            }
            obtained_exceptions = criteria.setFirstResult(offset).setMaxResults(limit).list();
        } catch (Exception e) {
            // throw new SQLException("Data error", e)
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtained_exceptions;
    }

    public Integer countTopByApplicationId(int app_id){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria= session.createCriteria(getInnerClass())
                    .createAlias("exceptionClass", "exceptionClass")
                    .createAlias("application", "application")
                    .add(Restrictions.eq("application.id", app_id))
                    .setProjection(Projections.projectionList()
                            .add(Projections.rowCount(), "count")
                            .add(Projections.groupProperty("exceptionClass.id"), "class")
                            .add(Projections.groupProperty("exceptionClass.exception_class"))
                    );
            obtained_exceptions = criteria.setMaxResults(10).list();
        } catch (Exception e) {
            // throw new SQLException("Data error", e)
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtained_exceptions.size();
    }

    public List<ObtainedException> loadByIdAndAppId(Integer app_id,Integer exc_id,Integer offset,String type,String field,Integer limit){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria= session.createCriteria(getInnerClass())
                                        .createAlias("application", "app")
                                        .add(Restrictions.eq("app.id", app_id))
                                        .createAlias("exceptionClass", "exc")
                                        .add(Restrictions.eq("exc.id", exc_id));
            if(field == null){
                criteria.addOrder(Order.desc("create_at"));
            }else {
                if(field.equals("class"))
                    field = "exc.id";
                if(field.equals("date"))
                    field = "create_at";
                if(type.equals("desc"))
                    criteria.addOrder(Order.desc(field));
                else
                    criteria.addOrder(Order.asc(field));
            }
            obtained_exceptions = criteria.setFirstResult(offset)
                                          .setMaxResults(limit)
                                          .list();
        } catch (Exception e) {
            //
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtained_exceptions;
    }

    public Integer countAll(Integer applicationId, Integer exception_id) {
        Integer count = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            count= session.createCriteria(getInnerClass())
                    .createAlias("application","app")
                    .add(Restrictions.eq("app.id",applicationId))
                    .createAlias("exceptionClass","exc")
                    .add(Restrictions.eq("exc.id",exception_id))
                    .list().size();
        } catch (Exception e) {
            //
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }

    public List<ObtainedException> loadByFilters(FilterObject obj,Integer offset,String sorting_field, String sorting_type,Integer limit) {
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria filter =session.createCriteria(getInnerClass())
                                    .createAlias("exceptionClass", "exc")
                                    .createAlias("application", "app")
                                    .createAlias("device", "dev");
            if(obj.isClassFilter()){
                filter.add(Restrictions.in("exc.id", obj.getClassesId()));
            }
            if(obj.isApplicationFilter()){
                filter.add(Restrictions.in("app.id", obj.getApplicationsId()));
            }
            if(obj.isDeviceFilter()){
                filter.add(Restrictions.in("dev.id",obj.getDevicesId()));
            }
            if(obj.isDateFilter()){
                HashMap<String,String> params= obj.getDateParameters();
                if(params.get("operation").equals("from_to")){
                    Date from = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("from"));
                    Date to = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("to"));
                    filter.add(Restrictions.between("create_at",from,to));
                }else if(params.get("operation").equals("from")){
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("date"));
                    filter.add(Restrictions.ge("create_at", date));
                }else if(params.get("operation").equals("to")){
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("date"));
                    filter.add(Restrictions.le("create_at", date));
                }else if(params.get("operation").equals("eq")){
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("date"));
                    filter.add(Restrictions.eq("create_at", date));
                }
            }
            if(obj.isGrouping()){
                ProjectionList projections = Projections.projectionList().add(Projections.rowCount(), "count");
                List<String> group = obj.getGroup_by();
                if(group.indexOf("date")== -1){
                    projections.add(Projections.min("create_at"));
                }else
                    projections.add(Projections.groupProperty("create_at"));

                if(group.indexOf("class")== -1){
                    projections.add(Projections.min("exc.id"),"exc.id").add(Projections.min("exc.exception_class"),"class");
                }else
                    projections.add(Projections.groupProperty("exc.id"),"exc.id").add(Projections.groupProperty("exc.exception_class"),"class");

                if(group.indexOf("application")== -1){
                    projections.add(Projections.min("app.id"),"app.id").add(Projections.min("app.name"),"app_name");
                }else
                    projections.add(Projections.groupProperty("app.id"),"app.id").add(Projections.groupProperty("app.name"),"app_name");

                if(group.indexOf("device")== -1){
                    projections.add(Projections.min("dev.id"),"device").add(Projections.min("dev.name"),"app_name");
                }else
                    projections.add(Projections.groupProperty("dev.id"),"device").add(Projections.groupProperty("dev.name"),"app_name");
                filter.setProjection(projections);
            }
            if(sorting_type.equals("asc")){
                filter.addOrder(Order.asc(sorting_field));
            }else
                filter.addOrder(Order.desc(sorting_field));

            obtained_exceptions =filter
                    .setMaxResults(limit)
                    .setFirstResult(offset)
                    .list();
        } catch (Exception e) {
            // throw new SQLException("Data error", e)
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtained_exceptions;
    }

    public ObtainedException getForView(Integer obtained_exception_id) {
        ObtainedException obtainedException = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            obtainedException= (ObtainedException) session.createCriteria(getInnerClass()).add(Restrictions.eq("id",obtained_exception_id)).uniqueResult();
        } catch (Exception e) {
            //
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtainedException;

    }

    public List<ObtainedException> loadAllExceptionClasses() {
        List<ObtainedException> obtainedExceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            obtainedExceptions= session.createCriteria(getInnerClass())
                    .createAlias("exceptionClass", "exceptionClass")
                    .createAlias("application", "application")
                    .setProjection(Projections.projectionList()
                            .add(Projections.rowCount())
                            .add(Projections.groupProperty("exceptionClass.id"))
                            .add(Projections.groupProperty("exceptionClass.exception_class"))
                    )
                    .list();
        } catch (Exception e) {
            //
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obtainedExceptions;
    }

    public Integer getTotalElements(FilterObject obj) {
        Integer total = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria filter =session.createCriteria(getInnerClass())
                    .createAlias("exceptionClass", "exc")
                    .createAlias("application", "app")
                    .createAlias("device", "dev");
            if(obj.isClassFilter()){
                filter.add(Restrictions.in("exc.id", obj.getClassesId()));
            }
            if(obj.isApplicationFilter()){
                filter.add(Restrictions.in("app.id", obj.getApplicationsId()));
            }
            if(obj.isDeviceFilter()){
                filter.add(Restrictions.in("dev.id",obj.getDevicesId()));
            }
            if(obj.isDateFilter()){
                HashMap<String,String> params= obj.getDateParameters();
                if(params.get("operation").equals("from_to")){
                    Date from = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("from"));
                    Date to = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("to"));
                    filter.add(Restrictions.between("create_at",from,to));
                }else if(params.get("operation").equals("from")){
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("date"));
                    filter.add(Restrictions.ge("create_at", date));
                }else if(params.get("operation").equals("to")){
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("date"));
                    filter.add(Restrictions.le("create_at", date));
                }else if(params.get("operation").equals("eq")){
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("date"));
                    filter.add(Restrictions.eq("create_at", date));
                }
            }
            if(obj.isGrouping()){
                ProjectionList projections = Projections.projectionList().add(Projections.rowCount(), "count").add(Projections.min("exc.exception_class"),"class");
                List<String> group = obj.getGroup_by();
                if(group.indexOf("date")== -1){
                    projections.add(Projections.min("create_at"));
                }else
                    projections.add(Projections.groupProperty("create_at"));

                if(group.indexOf("class")== -1){
                    projections.add(Projections.min("exc.id"),"exc.id");
                }else
                    projections.add(Projections.groupProperty("exc.id"));

                if(group.indexOf("application")== -1){
                    projections.add(Projections.min("app.id"));
                }else
                    projections.add(Projections.groupProperty("app.id"));

                if(group.indexOf("device")== -1){
                    projections.add(Projections.min("dev.id"));
                }else
                    projections.add(Projections.groupProperty("dev.id"));
                filter.setProjection(projections);
            }
            total =filter.list().size();
        } catch (Exception e) {
            // throw new SQLException("Data error", e)
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return total;
    }
}
