package org.opencrash.mvc;

import org.opencrash.ExceptionParser;
import org.opencrash.dao.HibernateDAO;
import org.opencrash.domain_objects.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class requestController {
	@RequestMapping(method = RequestMethod.GET)
	public String getRequest(ModelMap model) {
        Logger logger = Logger.getLogger("11");
        String json ="{\"client\":{\"name\": \"target-app-name\",\"version\": \"0.1\"},\"request\":{\"custom_data\":{\"key1\": \"value1\",\"key2\": \"value2\"},\"exception\":{\"body\": \"EXCEPTION MESSAGE + BACKTRACE\",\"message\": \"java.lang.RuntimeException: some exception message\",\"where\": \"SomeClass.java:58\",\"class\": \"java.lang.RuntimeException\",\"backtrace\":\"\"},\"application_environment\":{\"phone\": \"phone name + model\",\"appver\": \"1.2\",\"appname\": \"com.someapp\",\"osver\": \"4.1\",\"wifi_on\": \"true\",\"mobile_net_on\": \"true\",\"gps_on\": \"true\",\"screen_dpi(x:y)\": \"120.0:120.0\",\"screen:width\": \"240\",\"screen:height\": \"400\",\"screen:orientation\": \"normal\",\"uid\": \"SOME UNIQUE DEVICE IDENTIFIER\"},\"feedback\": {\"email\": \"test@bugsense.com\",\"description\": \"I just had a crash :(\"}}}";
        ExceptionParser Ex = new ExceptionParser(json);
        Ex.parse();
        String massage;
        List<User> Users=null;
        try {
            Users = HibernateDAO.getInstance().DAOUser().getAll();
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        if(Ex.checkExceptionBody())
             massage="111";
        else
            massage = "222";
        model.put("message", massage);
		return "hello";
	}
}