package com.example.demo;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class should process beans.
 */
public class Hello {
    private static final Logger LOG = LoggerFactory.getLogger(Hello.class);

    public final ServletContext servletContext;

    public Hello(ServletContext servletContext) {
        if (servletContext == null) {
            throw new IllegalArgumentException("servletContext is null");
        }
        this.servletContext = servletContext;
    }

    public void process(Object bean, String name) {
        LOG.warn("I am processing a bean {} name. servletContext={}", name, servletContext);

    }
}
