package br.com.sgpf.app.financeiro.application.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.application.FinanceiroTheme;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Layout principal do sistema, representa a estrutura geral da interface. 
 */
@UIScoped
public class MainLayout extends HorizontalLayout {
	private static final long serialVersionUID = -7373666438578675648L;
	
	@Inject
	private MainMenu mainMenu;

	private ComponentContainer viewContainer;
	
	@PostConstruct
	public void postConstruct() {
		setStyleName(FinanceiroTheme.STRUCT_MAIN_LAYOUT);
		setSizeFull();
	}

	/**
	 * Constrói o conteúdo do layout.
	 */
	public void buildContent() {		
		viewContainer = new CssLayout();
		viewContainer.addStyleName(FinanceiroTheme.STRUCT_VIEW_CONTENT);
		viewContainer.setSizeFull();
		
		mainMenu.buildContent();
		
		addComponents(mainMenu, viewContainer);
		setExpandRatio(viewContainer, 1);
	}

	public ComponentContainer getViewContainer() {
		return viewContainer;
	}
}
