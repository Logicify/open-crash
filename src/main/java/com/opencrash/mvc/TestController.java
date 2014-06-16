package com.opencrash.mvc;

import org.opencrash.domain_objects.AuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class TestController {
	@RequestMapping(method = RequestMethod.GET)
	public String getRequest(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null)
            authUser = new AuthUser();
        if (authUser.IsLogin().equals("true"))
            model.put("user","logged-in");
        else
            model.put("user","logged-out");
        return "index";
	}
}