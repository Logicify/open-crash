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

    public List<ObtainedException> loadTopByApplicationId(int app_id){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            obtained_exceptions = session.createCriteria(getInnerClass())
                                         .createAlias("exceptionClass", "exceptionClass")
                                         .createAlias("application", "application")
                                         .add(Restrictions.eq("application.id", app_id))
                                         .setProjection(Projections.projectionList()
                                                 .add(Projections.rowCount())
                                                 .add(Projections.groupProperty("exceptionClass.id"))
                                                 .add(Projections.groupProperty("exceptionClass.exception_class"))
                                         )
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

    public List<ObtainedException> loadByIdAndAppId(Integer app_id,Integer exc_id,Integer offset){
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            obtained_exceptions= session.createCriteria(getInnerClass())
                                        .createAlias("application", "app")
                                        .add(Restrictions.eq("app.id", app_id))
                                        .createAlias("exceptionClass", "exc")
                                        .add(Restrictions.eq("exc.id", exc_id))
                                        .setFirstResult(offset)
                                        .setMaxResults(10)
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

    public List<ObtainedException> loadByFilters(FilterObject obj,Integer offset,String sorting_field, String sorting_type) {
        List<ObtainedException> obtained_exceptions = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria filter =session.createCriteria(getInnerClass())
                                    .createAlias("exceptionClass", "exc")
                                    .createAlias("application", "app");
            if(obj.isClassFilter()){
                filter.add(Restrictions.in("exc.id", obj.getClassesId()));
            }
            if(obj.isApplicationFilter()){
                filter.add(Restrictions.in("app.id", obj.getApplicationsId()));
            }
            if(obj.isUserFilter()){
                filter.add(Restrictions.in("uid", obj.getUsersId()));
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
                    filter.add(Restrictions.eq("create_at",date));
                }
            }
            if(obj.isGrouping()){
                ProjectionList projections = Projections.projectionList().add(Projections.rowCount())
                        .add(Projections.min("create_at"))
                        .add(Projections.min("exc.id"))
                        .add(Projections.min("exc.exception_class"))
                        .add(Projections.min("app.id"))
                        .add(Projections.min("app.name"));
                for (int i=0;i<obj.getGroup_by().size();i++){
                    if(obj.getGroup_by().get(i).equals("date"))
                        projections.add(Projections.min("create_at"),"create_at").add(Projections.groupProperty("create_at"));
                    else if (obj.getGroup_by().get(i).equals("exceptionClass"))
                        projections.add(Projections.min("exc.id"),"exc.id").add(Projections.min("exc.exception_class")).add(Projections.groupProperty("exc.id"));
                    else if (obj.getGroup_by().get(i).equals("message"))
                        projections.add(Projections.min("message"),"message").add(Projections.groupProperty("message"));
                    else if (obj.getGroup_by().get(i).equals("application"))
                        projections.add(Projections.min("app.id"), "app.id").add(Projections.min("app.name")).add(Projections.groupProperty("app.id"));
                }
                filter.setProjection(projections);
            }else
            if(sorting_type.equals("asc")){
                filter.addOrder(Order.asc(sorting_field));
            }else
                filter.addOrder(Order.desc(sorting_field));

            obtained_exceptions =filter
                    .setMaxResults(10)
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
                    .createAlias("application", "app");
            if(obj.isClassFilter()){
                filter.add(Restrictions.in("exc.id", obj.getClassesId()));
            }
            if(obj.isApplicationFilter()){
                filter.add(Restrictions.in("app.id", obj.getApplicationsId()));
            }
            if(obj.isUserFilter()){
                filter.add(Restrictions.in("uid", obj.getUsersId()));
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
                }else if(params.get("operation").isEmpty()){
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(params.get("date"));
                    filter.add(Restrictions.eq("create_at",date));
                }
            }
            if(obj.isGrouping()){
                ProjectionList projections = Projections.projectionList().add(Projections.min("id"))
                        .add(Projections.min("message"),"message")
                        .add(Projections.min("create_at"),"create_at")
                        .add(Projections.min("exc.id"),"exc.id")
                        .add(Projections.min("exc.exception_class"))
                        .add(Projections.min("app.id"),"app.id")
                        .add(Projections.min("app.name"));
                for (int i=0;i<obj.getGroup_by().size();i++){
                    if(obj.getGroup_by().get(i).equals("date"))
                        projections.add(Projections.groupProperty("create_at"));
                    else if (obj.getGroup_by().get(i).equals("exceptionClass"))
                        projections.add(Projections.groupProperty("exc.id"));
                    else if (obj.getGroup_by().get(i).equals("message"))
                        projections.add(Projections.groupProperty("message"));
                    else if (obj.getGroup_by().get(i).equals("application"))
                        projections.add(Projections.groupProperty("app.id"));
                }
                filter.setProjection(projections);
            }
            total =filter
                    .list().size();
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
