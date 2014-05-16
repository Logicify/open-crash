package org.opencrash.api;

import org.opencrash.domain_objects.Exception_class;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public interface ExceptionClassService {

    public Exception_class getExceptionClass(String exception_class);

    public void AddNewClass(Exception_class newClass);

}
