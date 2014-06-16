package org.opencrash.api;

import org.opencrash.domain_objects.Application;
import org.opencrash.domain_objects.ParserObject;
import org.opencrash.util.exceptions.HandlerServiceException;

/**
 * Created by Fong on 13.05.14.
 */
public interface HandlerService {
    public void handleException(Application application,ParserObject obj) throws HandlerServiceException;
}
