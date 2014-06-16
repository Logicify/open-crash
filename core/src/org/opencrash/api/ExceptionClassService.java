package org.opencrash.api;

import org.opencrash.domain_objects.ExceptionClass;

/**
 * Created by Fong on 12.05.14.
 */
public interface ExceptionClassService {

    public ExceptionClass getExceptionClass(String exception_class);

    public void AddNewClass(ExceptionClass newClass);

}
