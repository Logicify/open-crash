package org.opencrash.dao.interfaces;

import org.opencrash.domain_objects.ObtainedException;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public interface DAOObtainedException extends DAOCRUD<ObtainedException> {
    public List<ObtainedException> loadTopByApplicationId(int app_id);
    public List<ObtainedException> loadByIdAndAppId(Integer app_id,Integer exc_id,Integer offset);
    public Integer countAll(Integer applicationId, Integer exception_id);
}
