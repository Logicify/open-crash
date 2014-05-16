package org.opencrash.api;

import org.opencrash.domain_objects.Application;

/**
 * Created by Fong on 13.05.14.
 */
public interface HandlerService {
    public void handleException(String exception_class_name,String exception_massage,String exception_backtrace,Application application,String uid);
}
