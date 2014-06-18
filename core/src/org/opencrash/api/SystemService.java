package org.opencrash.api;

import org.opencrash.domain_objects.MobileSystem;

import java.util.List;

/**
 * Created by Fong on 30.05.14.
 */
public interface SystemService {
    public MobileSystem getByVersion(String version);

    public void addNewSystem(MobileSystem mobileSystem);

    public MobileSystem getById(Integer system_id);

    public List<MobileSystem> loadAll();
}
