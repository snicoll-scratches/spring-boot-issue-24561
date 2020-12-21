package com.example.demo;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

@Configuration
public class DemoConfig implements DestructionAwareBeanPostProcessor, ServletContextAware {
	private static final Logger LOG = LoggerFactory.getLogger(DemoConfig.class);

	private ServletContext servletContext;

	@PostConstruct
	public void init() {
		LOG.warn("init() {}", servletContext);
	}

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		LOG.warn("setServletContext() {}", servletContext);
		this.servletContext = servletContext;
	}

}
