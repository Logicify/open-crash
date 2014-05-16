package org.opencrash.api;

import org.opencrash.domain_objects.Obtained_exception;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public interface ObtainedExceptionService {

    public void newObtainedException(Obtained_exception obtained_exception);
    public List<Object> getExceptionByApplication(Integer app_id);
    public List<Obtained_exception> getExceptionsByAppIdAndExId(Integer app_id,Integer exc_id,Integer offset);
}
