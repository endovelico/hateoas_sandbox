package com.hateoas.demo.interfaces;

import org.springframework.web.servlet.View;

import java.util.Locale;

public interface ViewResolver {

    View resolveView(String viewName, Locale locale) throws Exception;
}
