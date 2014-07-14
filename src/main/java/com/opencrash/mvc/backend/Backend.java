package com.opencrash.mvc.backend;

import org.opencrash.api.SystemService;
import org.opencrash.api.UserService;
import org.opencrash.domain_objects.AuthUser;
import org.opencrash.domain_objects.MobileSystem;
import org.opencrash.domain_objects.Register_user;
import org.opencrash.util.BackendUserValidation;
import org.opencrash.util.Security;
import org.opencrash.util.Settings;
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
 * Created by Fong on 11.07.14.
 */
@Controller
public class Backend {
    @Autowired
    SystemService systemService;
    UserService userService;

    @RequestMapping(value ="/backend/login",method = RequestMethod.GET)
    public String backendLogin(){
        return "/backend/login";
    }
    @RequestMapping(value = "/backend/log-in",method = RequestMethod.POST)
    public String  backendLogIn(HttpServletRequest request, final RedirectAttributes redirectAttrs){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String,String> errors = new HashMap<String,String>();
        BackendUserValidation backendUserValidation = new BackendUserValidation(username,password);
        backendUserValidation.validate();
        if(backendUserValidation.isValid()){
            Security security = new Security();
            String hash = security.getHashPassword(password);
            Register_user admin = userService.getAdminUser(username,hash);
            if(admin!=null){
                authUser.Login(admin.getUsername(),admin.getId());
                authUser.setAdmin(true);
                session.setAttribute("userInfo",authUser);
                return "redirect:/backend";
            }else {
                errors.put("user","this user doesn't exist");
                redirectAttrs.addFlashAttribute("errors",errors);
                redirectAttrs.addFlashAttribute("username",username);
                return "redirect:/backend/login";
            }
        }else{
            errors=backendUserValidation.getErrors();
            redirectAttrs.addFlashAttribute("errors",errors);
            redirectAttrs.addFlashAttribute("username",username);
            return "redirect:/backend/login";
        }
    }
    @RequestMapping(value = "/backend/settings/pagination",method = RequestMethod.GET)
    public String settings(HttpServletRequest request,ModelMap model){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.isAdmin()){
            Settings settings = new Settings();
            settings.getSettings();
            model.put("pagination",settings.getPagination());
           return "/backend/pagination";
        }else
            return "redirect:/";
    }
    @RequestMapping(value = "/backend/settings/save",method = RequestMethod.POST)
    public String settingsSave(HttpServletRequest request, final RedirectAttributes redirectAttrs){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.isAdmin()){
            String elements = request.getParameter("elements");
            Settings settings = new Settings();
            settings.getSettings();
            settings.setPagination(elements);
            settings.save();
            redirectAttrs.addFlashAttribute("success","true");
        }
        return "redirect:/backend/settings/pagination";
    }

    @RequestMapping(value = "/backend/settings/systems",method = RequestMethod.GET)
    public String mobileSystemsList( HttpServletRequest request,ModelMap model){
        if(!request.getAttribute("admin").equals("true"))
            return "redirect:/";
        List<MobileSystem> systems = systemService.loadAll();
        model.put("systems",systems);
        return "/backend/systems/list";
    }

    @RequestMapping(value = "/backend/settings/systems/edit/{systemId}",method = RequestMethod.GET)
    public String mobileSystemsEdit(@PathVariable("systemId") Integer systemId, HttpServletRequest request,ModelMap model){
        if(!request.getAttribute("admin").equals("true"))
            return "redirect:/";
        MobileSystem system = systemService.getById(systemId);
        model.put("system",system);
        return "/backend/systems/edit";
    }

    @RequestMapping(value = "/backend/settings/systems/edit/{systemId}",method = RequestMethod.POST)
    public String mobileSystemsEditPost(@PathVariable("systemId") Integer systemId, HttpServletRequest request,ModelMap model){
        if(!request.getAttribute("admin").equals("true"))
            return "redirect:/";
        MobileSystem system = systemService.getById(systemId);
        String systemName = request.getParameter("systemName");
        system.setName(systemName);
        systemService.saveEdit(system);
        return "redirect:/backend/settings/systems";
    }
    @RequestMapping(value = "/backend/settings/systems/delete/{systemId}",method = RequestMethod.GET)
    public String mobileSystemsDelete(@PathVariable("systemId") Integer systemId, HttpServletRequest request,ModelMap model){
        if(!request.getAttribute("admin").equals("true"))
            return "redirect:/";
        MobileSystem system = systemService.getById(systemId);
        if(system!= null){
            model.put("system",system);
            return "/backend/systems/delete";
        }else
            return "redirect:/backend/settings/systems/delete";
    }

    @RequestMapping(value = "/backend/settings/systems/delete/{systemId}",method = RequestMethod.POST)
    public String mobileSystemsDeletePost(@PathVariable("systemId") Integer systemId, HttpServletRequest request,ModelMap model){
        if(!request.getAttribute("admin").equals("true"))
            return "redirect:/";
        MobileSystem system = systemService.getById(systemId);
        if(system != null){
            systemService.deleteSystem(system);
        }
        return "redirect:/backend/settings/systems";
    }
    @RequestMapping(value = "/backend/settings/systems/add",method = RequestMethod.GET)
    public String newSystem(HttpServletRequest request,ModelMap model){
        if(!request.getAttribute("admin").equals("true"))
            return "redirect:/";
        return "redirect:/backend/systems/add";
    }
    @RequestMapping(value = "/backend/settings/systems/add",method = RequestMethod.POST)
    public String newSystemPost(HttpServletRequest request){
        if(request.getAttribute("admin").equals("true")){
            String systemName = request.getParameter("systemName");
            MobileSystem mobileSystem = new MobileSystem();
            mobileSystem.setName(systemName);
            systemService.addNewSystem(mobileSystem);
        }
        return "redirect://backend/settings/systems";
    }
}
