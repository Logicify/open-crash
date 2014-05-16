package org.opencrash.mvc.frontend.user.account;

import org.opencrash.api.ApplicationService;
import org.opencrash.api.ObtainedExceptionService;
import org.opencrash.api.UserService;
import org.opencrash.api.implementation.ApplicationServiceImpl;
import org.opencrash.api.implementation.ObtainedExceptionServiceImpl;
import org.opencrash.api.implementation.UserServiceImpl;
import org.opencrash.domain_objects.Application;
import org.opencrash.domain_objects.AuthUser;
import org.opencrash.domain_objects.Obtained_exception;
import org.opencrash.domain_objects.Register_user;
import org.opencrash.util.ApplicationValidator;
import org.opencrash.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Fong on 15.05.14.
 */
@Controller
public class userController {

    @RequestMapping(value="/myaccount",method = RequestMethod.GET)
    public String getMyAccount(ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
        ApplicationService applicationService = new ApplicationServiceImpl();
        applicationService.loadApplicationByUser(authUser.getUser_id());
        List<Application> apps = applicationService.loadApplicationByUser(authUser.getUser_id());
        model.put("apps",apps);
        return "user/myaccount";
    }

   @RequestMapping(value = "/myaccount/applications/add",method = RequestMethod.GET)
    public  String getAddNewApplication(HttpServletRequest request){
       HttpSession session = request.getSession();
       AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
       if (authUser == null)
           authUser = new AuthUser();
       if (authUser.IsLogin().equals("false"))
           return "redirect:/login";
       return "user/application_add";
    }
    @RequestMapping(value = "/myaccount/applications/add",method = RequestMethod.POST)
    public String postAddNewApplication(HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");

        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";

        String application_name = request.getParameter("app_name");
        String application_version = request.getParameter("app_version");
        ApplicationValidator applicationValidator = new ApplicationValidator(application_name,application_version);
        applicationValidator.validate();
        if(applicationValidator.valid()){
            Application application  = applicationValidator.buildObject();
            Security security = new Security();
            application.setApplication_key(security.getHashPassword(application_name));
            UserService userService = new UserServiceImpl();
            Register_user user = userService.getByid(authUser.getUser_id());
            application.setRegister_user(user);
            ApplicationService applicationService = new ApplicationServiceImpl();
            applicationService.newApplication(application);
            return "redirect:/myaccount";
        }
        return "user/application_add";
    }
    @RequestMapping(value = "/myaccount/application/{applicationId}",method = RequestMethod.GET)
    public String getViewApplication(@PathVariable("applicationId") Integer applicationId,ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
        ApplicationService applicationService = new ApplicationServiceImpl();
        Application application = applicationService.getById(applicationId);
        if(application ==null)
            return "redirect:/myaccount";
        ObtainedExceptionService obtainedExceptionService = new ObtainedExceptionServiceImpl();
        List<Object> obtained_exceptions = obtainedExceptionService.getExceptionByApplication(applicationId);
        model.put("applicationId",applicationId);
        model.put("top_exceptions",obtained_exceptions);
        return "user/application";
    }

    @RequestMapping(value ="/myaccount/application/{applicationId}/exception/list/{exception_id}",method = RequestMethod.GET)
    public String exceptionsList(@PathVariable("applicationId") Integer applicationId,@PathVariable("exception_id")Integer exception_id, ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
        ObtainedExceptionService obtainedExceptionService = new ObtainedExceptionServiceImpl();
        List<Obtained_exception> obtained_exceptions = obtainedExceptionService.getExceptionsByAppIdAndExId(applicationId,exception_id,0);
        model.put("exceptions",obtained_exceptions);
        return "user/exceptions-list";
    }

    @RequestMapping(value ="/myaccount/application/{applicationId}/exception/list/{exception_id}/page-{page}",method = RequestMethod.GET)
    public String exceptionsListPage(@PathVariable("applicationId") Integer applicationId,@PathVariable("exception_id")Integer exception_id, ModelMap model,HttpServletRequest request,@PathVariable("page")Integer page){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
        ObtainedExceptionService obtainedExceptionService = new ObtainedExceptionServiceImpl();
        int offset = (page - 1) * 10;
        List<Obtained_exception> obtained_exceptions = obtainedExceptionService.getExceptionsByAppIdAndExId(applicationId,exception_id,offset);
        model.put("exceptions",obtained_exceptions);
        return "user/exceptions-list";
    }
}
