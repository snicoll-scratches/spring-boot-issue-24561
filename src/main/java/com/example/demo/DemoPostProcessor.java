package com.example.demo;

import java.util.Optional;

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

	Optional<Hello> getHello() {
		if (applicationContext.getServletContext() != null) {
			// Once we have servletContext I want to instantiate my Hello bean
			// Unfortunately, this fails with:
			//
			// Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException:
			// Error creating bean with name 'hello':
			// Requested bean is currently in creation: Is there an unresolvable circular reference?

			return Optional.of(applicationContext.getBean(Hello.class));
		}

		return Optional.empty();
	}

    @Override
    public Object postProcessBeforeInitialization( Object bean, String name ) throws BeansException {
		Optional<Hello> found = getHello();
		if (found.isPresent()) {
			found.get().process(bean, name);
		} else {
			LOG.warn("Hello not found yet {}", name);
		}
        return bean;
    }

	@Override
	public void postProcessBeforeDestruction(Object bean, String name) throws BeansException {
		// LOG.warn("Destruction of {} with servletContext {}", name, getHello().get().servletContext);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (WebApplicationContext) applicationContext;
	}
}
