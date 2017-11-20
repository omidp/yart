package com.yart.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yart.util.AuthenticationUtil;

public class SecurityHandlerInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        request.setAttribute("loggedIn", AuthenticationUtil.isLoggedIn());
        request.setAttribute("anonymous", AuthenticationUtil.isAnonymous());
        request.setAttribute("currentUserName", AuthenticationUtil.getUsername());
        request.setAttribute("currentUserId", AuthenticationUtil.getUserId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }

}
