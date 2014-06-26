package org.opencrash.api;

import org.opencrash.domain_objects.FilterObject;
import org.opencrash.domain_objects.ObtainedException;

import java.util.List;

/**
 * Created by Fong on 19.06.14.
 */
public interface AjaxService {
    public List<ObtainedException> loadByFilters( FilterObject obj,Integer offset);
    public Integer getTotalElements(FilterObject filterObject);
}
