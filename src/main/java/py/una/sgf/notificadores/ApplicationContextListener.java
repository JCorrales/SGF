package py.una.sgf.notificadores;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.main.ApplicationContextProvider;

@Component
public class ApplicationContextListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		if (event instanceof ContextRefreshedEvent) {
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) ApplicationContextProvider.getContext()
					.getAutowireCapableBeanFactory();

			BeanDefinition old = registry.getBeanDefinition("usuarioBCImpl");
			old.setAutowireCandidate(false);
			old.setPrimary(false);
			registry.removeBeanDefinition("usuarioBCImpl");

			BeanDefinition newdef = registry.getBeanDefinition("usuarioBCImpl2");
			newdef.setPrimary(true);

			registry.registerBeanDefinition("usuarioBCImpl", newdef);
		}
	}

}
