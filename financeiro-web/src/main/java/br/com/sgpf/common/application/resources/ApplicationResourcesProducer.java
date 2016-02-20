package br.com.sgpf.common.application.resources;

import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.vaadin.ui.UI;

/**
 * Classe produtora de recursos da aplicação
 */
@ApplicationScoped
public class ApplicationResourcesProducer {

	/**
	 * Produz o locale da aplicação.
	 * 
	 * @return Locale da aplicação
	 */
	@Produces
	public Locale localeProducer() {
		return UI.getCurrent().getLocale();
	}
}
