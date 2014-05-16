package org.opencrash.mvc.frontend.user;

import org.opencrash.api.UserService;
import org.opencrash.api.implementation.UserServiceImpl;
import org.opencrash.domain_objects.AuthUser;
import org.opencrash.domain_objects.Register_user;
import org.opencrash.util.RegisterUserValidator;
import org.opencrash.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller

public class Auth {

	@RequestMapping(value = "/registration",method = RequestMethod.GET)
	public String getRegistration(ModelMap model) {
        return "registration";
	}
    @RequestMapping(value ="/registration",method = RequestMethod.POST)
    public String postRegistration(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String retype_password = request.getParameter("check_password");
        String email = request.getParameter("email");

        RegisterUserValidator registerUserValidator = new RegisterUserValidator(username,password,retype_password,email);
        registerUserValidator.validate();
        if(registerUserValidator.isValid()){
            Register_user user = registerUserValidator.objectBuilder();
            UserService userService = new UserServiceImpl();
            userService.addUser(user);
            return "redirect:/login";
        }
        return "registration";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLogin(ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("true"))
            return "redirect:/";
        else
            return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String postLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        RegisterUserValidator registerUserValidator = new RegisterUserValidator("",password,password,email);
        registerUserValidator.validateForLogin();
        Register_user register_user = null;
        if(registerUserValidator.isValid()){
           UserService userService = new UserServiceImpl();
            Security security = new Security();
            String hash = security.getHashPassword(password);
            register_user = userService.getUser(email,hash);
            if(register_user!=null){
                authUser.Login(register_user.getUsername(),register_user.getId());
                session.setAttribute("userInfo",authUser);
                return "redirect:/";
            }

        }

        return "/login";
    }
}