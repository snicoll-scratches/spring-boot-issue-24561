package com.example.demo;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

@Configuration
public class AnotherConfig implements ServletContextAware {
	private static final Logger LOG = LoggerFactory.getLogger( AnotherConfig.class );

	private ServletContext servletContext;

	@PostConstruct
	public void init() {
		LOG.warn( "init() {}", servletContext );
	}

	@Override
	public void setServletContext( ServletContext servletContext ) {
		LOG.warn( "setServletContext() {}", servletContext );
		this.servletContext = servletContext;
	}

}