package com.opencrash.mvc.frontend.user.account;

import org.opencrash.api.ApplicationService;
import org.opencrash.api.ObtainedExceptionService;
import org.opencrash.api.SystemService;
import org.opencrash.api.UserService;
import org.opencrash.api.implementation.ObtainedExceptionServiceImpl;
import org.opencrash.api.implementation.UserServiceImpl;
import org.opencrash.domain_objects.*;
import org.opencrash.util.ApplicationValidator;
import org.opencrash.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Fong on 15.05.14.
 */
@Controller
public class userController {
    @Autowired
    private  ApplicationService applicationService;
    @Autowired
    private SystemService systemService;

    @RequestMapping(value="/myaccount",method = RequestMethod.GET)
    public String getMyAccount(ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
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
    public String postAddNewApplication(HttpServletRequest request, final RedirectAttributes redirectAttrs){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");

        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";

        String application_name = request.getParameter("app_name");
        String application_version = request.getParameter("app_version");
        Integer system_id = Integer.parseInt(request.getParameter("mobileSystem"));
        MobileSystem system = systemService.getById(system_id);
        //MobileSystem system = new MobileSystem();
        //system.setId(1);
        Map<String,String> errors = new HashMap<String, String>();
        ApplicationValidator applicationValidator = new ApplicationValidator(application_name,application_version,system);
        applicationValidator.validate();
        if(applicationValidator.valid()){
            Application application  = applicationValidator.buildObject();
            Security security = new Security();
            UserService userService = new UserServiceImpl();
            Register_user user = userService.getByid(authUser.getUser_id());
            application.setRegister_user(user);
            application.setApplication_key(security.getHashKey(application_name,application_version,user.getUsername()));
            applicationService.newApplication(application);
            return "redirect:/myaccount";
        }else {
            errors = applicationValidator.getErrors();
            redirectAttrs.addFlashAttribute("errors",errors);
            return "redirect:/myaccount/applications/add";
        }
    }
    @RequestMapping(value = "/myaccount/application/{applicationId}",method = RequestMethod.GET)
    public String getViewApplication(@PathVariable("applicationId") Integer applicationId,ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
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
        List<ObtainedException> obtained_exceptions = obtainedExceptionService.getExceptionsByAppIdAndExId(applicationId,exception_id,0);
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
        List<ObtainedException> obtained_exceptions = obtainedExceptionService.getExceptionsByAppIdAndExId(applicationId,exception_id,offset);
        model.put("exceptions",obtained_exceptions);
        return "user/exceptions-list";
    }

    @RequestMapping(value = "/myaccount/application/edit/{application_id}",method = RequestMethod.GET)
    public String getEditApp(ModelMap model,@PathVariable("application_id") Integer application_id,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
        Application application = applicationService.getById(application_id);
        Integer user_id =application.getRegister_user().getId();
        if(application != null && user_id.equals(authUser.getUser_id())){
            model.put("application",application);
            return "/user/application-edit";
        }
        else {
            return "redirect:/myaccount";
        }
    }

    @RequestMapping(value = "/myaccount/application/edit/{application_id}",method = RequestMethod.POST)
    public String postEditApp(ModelMap model,@PathVariable("application_id") Integer application_id,HttpServletRequest request,final RedirectAttributes redirectAttrs){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("false"))
            return "redirect:/login";
        Application application =applicationService.getById(application_id);
        Integer user_id = application.getRegister_user().getId();
        if(user_id.equals(authUser.getUser_id())){
            Map<String,String> errors;
            String app_name = request.getParameter("app_name");
            String app_version = request.getParameter("app_version");
            String system_id = request.getParameter("system_id");
            MobileSystem system= new MobileSystem();
            ApplicationValidator applicationValidator = new ApplicationValidator(app_name,app_version,system);
            applicationValidator.validate();
            if(applicationValidator.valid()){
                application.setName(app_name);
                application.setVersion(app_version);
                applicationService.UpdateApplication(application);
                return "redirect:/myaccount";
            }else{
                errors = applicationValidator.getErrors();
                redirectAttrs.addFlashAttribute("errors",errors);
                redirectAttrs.addFlashAttribute("application_name",app_name);
                redirectAttrs.addFlashAttribute("application_version",app_version);
                return "redirect:/myaccount/application/edit/"+application_id;
            }
        }
        else
            return "redirect:/myaccount";
    }
}