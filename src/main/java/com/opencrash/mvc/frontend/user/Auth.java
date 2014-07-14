package com.opencrash.mvc.frontend.user;

import org.opencrash.api.SendMailService;
import org.opencrash.api.UserService;
import org.opencrash.api.implementation.SendMailServiceImpl;
import org.opencrash.api.implementation.UserServiceImpl;
import org.opencrash.domain_objects.AuthUser;
import org.opencrash.domain_objects.Register_user;
import org.opencrash.util.RegisterUserValidator;
import org.opencrash.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Auth {

	@RequestMapping(value = "/registration",method = RequestMethod.GET)
	public String getRegistration(ModelMap model,HttpServletRequest request) {
        if(request.getAttribute("user").equals("logged-in"))
            return "redirect:/";
        return "registration";
	}
    @RequestMapping(value ="/registration",method = RequestMethod.POST)
    public String postRegistration(HttpServletRequest request, final RedirectAttributes redirectAttrs){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String retype_password = request.getParameter("check_password");
        String email = request.getParameter("email");
        Map<String,String>errors = new HashMap<String, String>();

        RegisterUserValidator registerUserValidator = new RegisterUserValidator(username,password,retype_password,email);
        registerUserValidator.validate();
        if(registerUserValidator.isValid()){
            Register_user user = registerUserValidator.objectBuilder();
            UserService userService = new UserServiceImpl();
            userService.addUser(user);
            Security security = new Security();
            String token = security.getUserToken(user.getEmail(),user.getId());
            String url =request.getRequestURL()+"/confirm/"+user.getId()+"/"+token;
            SendMailService sendMailService = new SendMailServiceImpl();
            sendMailService.sendMail(user.getEmail(),"User activation",url);
            return "redirect:/login";
        }
        else
        {
            errors = registerUserValidator.getErrors();
            redirectAttrs.addFlashAttribute("errors",errors);
            redirectAttrs.addFlashAttribute("username",username);
            redirectAttrs.addFlashAttribute("email",email);
            return "redirect:/registration";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLogin(ModelMap model,HttpServletRequest request){
        if(request.getAttribute("user").equals("logged-in"))
            return "redirect:/";
        return "login";

    }
    @RequestMapping(value = "/logged-in",method = RequestMethod.POST)
    public String postLogin(HttpServletRequest request, final RedirectAttributes redirectAttrs){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String,String> errors = new HashMap<String,String>();
        RegisterUserValidator registerUserValidator = new RegisterUserValidator("",password,password,email);
        registerUserValidator.validateForLogin();
        Register_user register_user = null;
        if(registerUserValidator.isValid()){
           UserService userService = new UserServiceImpl();
            Security security = new Security();
            String hash = security.getHashPassword(password);
            register_user = userService.getUser(email,hash);
            if(register_user!=null){
                if(!register_user.isActivate()){
                    authUser.setUser_id(register_user.getId());
                    session.setAttribute("userInfo",authUser);
                    return "redirect:/registration/confirm/"+register_user.getId();
                }else{
                    authUser.Login(register_user.getUsername(),register_user.getId());
                    if(register_user.isAdmin())
                        authUser.setAdmin(true);
                    session.setAttribute("userInfo",authUser);
                    return "redirect:/";
                }
            }
            else{
                errors.put("user","this user doesn't exist");
                redirectAttrs.addFlashAttribute("errors",errors);
                redirectAttrs.addFlashAttribute("email",email);
                return "redirect:/login";
            }
        }{
            errors=registerUserValidator.getErrors();
            redirectAttrs.addFlashAttribute("errors",errors);
            redirectAttrs.addFlashAttribute("email",email);
            return "redirect:/login";
        }
    }
    @RequestMapping(value = "/registration/confirm/{uid}/{token}")
    public String registrationConfirm(@PathVariable("uid") Integer uid,@PathVariable("token") String token,ModelMap model,HttpServletRequest request){
        UserService userService = new UserServiceImpl();
        Register_user user= userService.getByid(uid);
        String message = null;
        if(user !=null){
            if(!user.isActivate()){
                Security security = new Security();
                String check_token = security.getUserToken(user.getEmail(),user.getId());
                if(check_token.equals(token)){
                    user.setActivate(true);
                    userService.updateUser(user);
                    message="activation successfully completed";
                }else
                    message="Wrong user token";
            }else
                message = "User is already activated";
        }

        model.put("message", message);
        return "registration-confirm";
    }
    @RequestMapping(value = "/registration/confirm/{uid}",method = RequestMethod.GET)
    public String getUserActivation(@PathVariable("uid")Integer uid,ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("true"))
            return "redirect:/";
        if(authUser.getUser_id().equals(uid)){
            model.put("uid",uid);
            model.put("user","logged-out");
            return "user-activation";
        }else
        return "redirect:/";
    }

    @RequestMapping(value = "/registration/confirm/{uid}",method = RequestMethod.POST)
    public String postUserActivation(@PathVariable("uid")Integer uid,HttpServletRequest request,final RedirectAttributes redirectAttrs){
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("true"))
            return "redirect:/";
        if(authUser.getUser_id().equals(uid)){
            UserService userService = new UserServiceImpl();
            Register_user user = userService.getByid(uid);
            if(user!=null){
                if(!user.isActivate()){
                    Security security = new Security();
                    String token = security.getUserToken(user.getEmail(),user.getId());
                    String url =request.getRequestURL()+"/"+token;
                    SendMailService sendMailService = new SendMailServiceImpl();
                    sendMailService.sendMail(user.getEmail(),"User activation",url);
                    redirectAttrs.addFlashAttribute("message","Letter was sended. Check your e-mail box");
                    return "redirect:/registration/confirm/"+uid;
                }else
                    return "redirect:/";
            }else{
                redirectAttrs.addFlashAttribute("message","Wrong user");
                return "redirect:/registration/confirm/"+uid;
            }
        }else
            return "redirect:/";
    }
   @RequestMapping(value = "/log-out",method = RequestMethod.GET)
    public String log_out(HttpServletRequest request){
       HttpSession session = request.getSession();
       AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
       if (authUser == null)
           authUser = new AuthUser();
       if (authUser.IsLogin().equals("false"))
           return "redirect:/";
       else{
           authUser.Logout();
           session.setAttribute("userInfo",authUser);
           return "redirect:/";
       }
    }
}