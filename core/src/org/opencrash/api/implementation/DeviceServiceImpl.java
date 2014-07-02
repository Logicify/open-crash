package org.opencrash.api.implementation;

import org.opencrash.api.DeviceService;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 02.07.14.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    Logger logger = Logger.getLogger(DeviceService.class.getName());

    public Device getByModel(String model){
        Device device = null;
        try {
            device = HibernateDAO.getInstance().DAODevice().getByModel(model);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return device;
    }
    public void addNew(Device device){
        try {
            HibernateDAO.getInstance().DAODevice().add(device);
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
    }
    public List<Device> loadAll(){
        List<Device> devices = null;
        try {
            devices = HibernateDAO.getInstance().DAODevice().getAll();
        }catch (Exception e){
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return devices;
    }
}
