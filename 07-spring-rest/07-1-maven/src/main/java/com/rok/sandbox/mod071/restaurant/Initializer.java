package com.rok.sandbox.mod071.restaurant;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    @Override
    protected Class[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
