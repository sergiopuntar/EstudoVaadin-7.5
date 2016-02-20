package br.com.sgpf.app.financeiro.application;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.application.view.MainLayout;
import br.com.sgpf.common.infra.event.After;
import br.com.sgpf.common.infra.event.Before;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Classe de inicialização da interface do sistema.
 */
@Title("Financeiro")
@Theme("financeiro")
@CDIUI("")
public class FinanceiroUI extends UI {
	private static final long serialVersionUID = -2076242544369939556L;
	
	private static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");
	
	@Inject
	private MainLayout mainLayout;

	@Inject
	private CDIViewProvider cdiViewProvider;
	
	@Inject @Before
	private javax.enterprise.event.Event<ViewChangeEvent> beforeViewChangeEvent;
	
	@Inject @After
	private javax.enterprise.event.Event<ViewChangeEvent> afterViewChangeEvent;
	
	@PostConstruct
	public void postConstruct() {
		setLocale(DEFAULT_LOCALE);		
	}
	
	@Override
	protected void init(VaadinRequest request) {
		mainLayout.buildContent();
		setContent(mainLayout);
		
		Navigator navigator = new Navigator(this, mainLayout.getViewContainer());
		navigator.addProvider(cdiViewProvider);
		navigator.addViewChangeListener(new ViewChangeListener() {
			private static final long serialVersionUID = -3803749349319455273L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				beforeViewChangeEvent.fire(event);
				return true;
			}
			
			@Override
			public void afterViewChange(ViewChangeEvent event) {
				afterViewChangeEvent.fire(event);
			}
		});
	}
}
