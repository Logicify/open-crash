package com.opencrash.mvc;

import org.opencrash.api.ApplicationService;
import org.opencrash.domain_objects.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Fong on 26.05.14.
 */
public class Interceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ApplicationService applicationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("userInfo");
        if (authUser == null){
            authUser = new AuthUser();
            request.setAttribute("user","logged-out");
        }
        if(authUser.IsLogin().equals("true")){
            request.setAttribute("user","logged-in");
            if(request.getRequestURI().startsWith("/myaccount"))
                request.setAttribute("apps",applicationService.loadApplicationByUser(authUser.getUser_id()));
        }else
            request.setAttribute("user","logged-out");

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
