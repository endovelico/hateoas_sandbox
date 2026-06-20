package com.hateoas.demo.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.web.servlet.ModelAndView;

public interface ExceptionHandlerResolver {

    ModelAndView resolveException(HttpServletRequest request, ExpiresFilter.XHttpServletResponse response, Object handler, Exception ex);

}
