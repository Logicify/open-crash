package org.opencrash.mvc;

import org.opencrash.ExceptionParser;
import org.opencrash.api.ApplicationService;
import org.opencrash.api.HandlerService;
import org.opencrash.api.implementation.ApplicationServiceImpl;
import org.opencrash.api.implementation.HandlerServiceImpl;
import org.opencrash.domain_objects.Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class requestController {
	@RequestMapping(method = RequestMethod.GET)
	public String getRequest(ModelMap model) {
        Logger logger = Logger.getLogger("11");
        String json ="{\"client\":{\"name\": \"target-app-name\",\"version\": \"0.1\"},\"request\":{\"custom_data\":{\"key1\": \"value1\",\"key2\": \"value2\"},\"exception\":{\"body\": \"EXCEPTION MESSAGE + BACKTRACE\",\"message\": \"java.lang.RuntimeException: some exception message\",\"where\": \"SomeClass.java:58\",\"class\": \"java.lang.RuntimeException\",\"backtrace\":\"org.postgresql.util.PSQLException: Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections. at org.postgresql.core.v3.ConnectionFactoryImpl.openConnectionImpl(ConnectionFactoryImpl.java:207 at org.postgresql.core.ConnectionFactory.openConnection(ConnectionFactory.java:64))\"},\"application_environment\":{\"phone\": \"phone name + model\",\"appver\": \"1.2\",\"appname\": \"com.someapp\",\"osver\": \"4.1\",\"wifi_on\": \"true\",\"mobile_net_on\": \"true\",\"gps_on\": \"true\",\"screen_dpi(x:y)\": \"120.0:120.0\",\"screen:width\": \"240\",\"screen:height\": \"400\",\"screen:orientation\": \"normal\",\"uid\": \"SOME UNIQUE DEVICE IDENTIFIER\"},\"feedback\": {\"email\": \"test@bugsense.com\",\"description\": \"I just had a crash :(\"}}}";
        ExceptionParser Ex = new ExceptionParser(json);
        Ex.parse();
        String exception_class_name = Ex.getExceptionClass();
        String exception_massage = Ex.getExceptionMassage();
        String exception_backtrace = Ex.getExceptionBacktrace();
        String app_name = Ex.getApplicationName();
        String app_ver = Ex.getAppVersion();
        String uid = "11z22c5";
        ApplicationService applicationService = new ApplicationServiceImpl();
        Application application  = applicationService.getApplication(app_name,app_ver);
        if(application != null){
            HandlerService handlerService = new HandlerServiceImpl();
            handlerService.handleException(exception_class_name,exception_massage,exception_backtrace,application,uid);
        }
        return "hello";
	}
}