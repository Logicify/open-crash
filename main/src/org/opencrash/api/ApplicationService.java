package org.opencrash.api;

import org.opencrash.domain_objects.Application;

import java.util.List;

/**
 * Created by Fong on 12.05.14.
 */
public interface ApplicationService {

    public Application getApplication(String app_name,String app_ver);
    public void  newApplication(Application application);
    public List<Application> loadApplicationByUser(int user_id);
    public Application getById(Integer id);

}
