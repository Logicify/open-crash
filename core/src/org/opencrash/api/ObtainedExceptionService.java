package org.opencrash.api;

import org.opencrash.domain_objects.ObtainedException;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public interface ObtainedExceptionService {

    public void newObtainedException(ObtainedException obtained_exception);
    public List<ObtainedException> getExceptionByApplication(Integer app_id);
    public List<ObtainedException> getExceptionsByAppIdAndExId(Integer app_id,Integer exc_id,Integer offset);
    public Integer getCount(Integer applicationId, Integer exception_id);
    public ObtainedException getForView(Integer obtained_exception_id);
    public List<ObtainedException> loadAllExceptionClasses();
}
