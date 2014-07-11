package com.opencrash.mvc.backend;

import org.opencrash.api.UserService;
import org.opencrash.domain_objects.AuthUser;
import org.opencrash.domain_objects.Register_user;
import org.opencrash.util.BackendUserValidation;
import org.opencrash.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fong on 11.07.14.
 */
@Controller
public class Backend {
    @Autowired
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
}
