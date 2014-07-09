package org.opencrash.api;

import org.opencrash.domain_objects.ObtainedException;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public interface ObtainedExceptionService {

    public void newObtainedException(ObtainedException obtained_exception);
    public List<ObtainedException> getExceptionByApplication(Integer app_id,String field,String type,Integer offset);
    public List<ObtainedException> getExceptionsByAppIdAndExId(Integer app_id,Integer exc_id,Integer offset,String type,String field);
    public Integer getCount(Integer applicationId, Integer exception_id);
    public ObtainedException getForView(Integer obtained_exception_id);
    public List<ObtainedException> loadAllExceptionClasses();
    public Integer countTopByApplicationId(Integer app_id);
}
