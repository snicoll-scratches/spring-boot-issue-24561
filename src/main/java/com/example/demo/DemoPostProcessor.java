package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

/**
 * A sample {@link DestructionAwareBeanPostProcessor} that defer access to the
 * servlet context.
 *
 * @author Stephane Nicoll
 */
public class DemoPostProcessor implements DestructionAwareBeanPostProcessor, ApplicationContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(DemoPostProcessor.class);

	private WebApplicationContext applicationContext;


	@Override
	public void postProcessBeforeDestruction(Object o, String s) throws BeansException {
		LOG.warn("Destruction of {} with servletContext {}", s, this.applicationContext.getServletContext());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (WebApplicationContext) applicationContext;
	}
}
