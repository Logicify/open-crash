package org.opencrash.api;

import org.opencrash.domain_objects.Device;

import java.util.List;

/**
 * Created by Fong on 02.07.14.
 */
public interface DeviceService {
    public Device getByModel(String model);

    public void addNew(Device device);
    public List<Device> loadAll();
}
