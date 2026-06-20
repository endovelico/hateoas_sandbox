package com.hateoas.demo.interfaces;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface HandlerInterceptor {

    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex);

    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView);

    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object);

}