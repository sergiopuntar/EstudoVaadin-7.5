package br.com.sgpf.app.financeiro.application.view;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_NAME;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DASHBOARD_NAME;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NAME;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;

import br.com.sgpf.app.financeiro.application.FinanceiroTheme;
import br.com.sgpf.app.financeiro.application.view.contas.ContasView;
import br.com.sgpf.app.financeiro.application.view.dashboard.DashboardView;
import br.com.sgpf.app.financeiro.application.view.dominios.DominiosView;
import br.com.sgpf.common.infra.event.Before;
import br.com.sgpf.common.infra.i18n.MessageKey;

import com.google.gwt.thirdparty.guava.common.collect.Maps;
import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Menu principal do sistema.
 */
@UIScoped
public class MainMenu extends CssLayout {
	private static final long serialVersionUID = 7309291644155844863L;
	
	private Map<Itens, MenuItem> menuItens = Maps.newHashMap();
	
	@PostConstruct
	public void postConstruct() {
		setPrimaryStyleName(ValoTheme.MENU_ROOT);
		setSizeUndefined();
	}

	/**
	 * Constrói o conteúdo do menu.
	 */
	public void buildContent() {
		VerticalLayout menuContent = new VerticalLayout();
		menuContent.addStyleName(ValoTheme.MENU_PART);
		menuContent.setHeight(100, Unit.PERCENTAGE);
		menuContent.addComponents(buildTitle(), buildItens());
		menuContent.setExpandRatio(menuContent.getComponent(menuContent.getComponentCount() - 1), 1);
		
		addComponent(menuContent);
	}

	/**
	 * Constrói o título do menú;
	 */
	private Component buildTitle() {
		CssLayout titleLayout = new CssLayout();
		Image logo = new Image(null, new ThemeResource("img/money-icon-128.png"));
		logo.addStyleName(FinanceiroTheme.LOGO);
		titleLayout.addComponent(logo);
		
		return titleLayout;
	}

	/**
	 * Constrói os itens do menu.
	 */
	private Component buildItens() {
		CssLayout itensLayout = new CssLayout();
		itensLayout.setWidth(100, Unit.PERCENTAGE);
		
		for (Itens item : Itens.values()) {
			menuItens.put(item, new MenuItem(item));
			itensLayout.addComponent(menuItens.get(item));
		}
		
		return itensLayout;
	}
	
	/**
	 * Enumeração de itens do menu.
	 */
	public static enum Itens {
		DASHBOARD (VIEW_DASHBOARD_NAME, DashboardView.VIEW_NAME, FontAwesome.HOME),
		CONTAS (VIEW_CONTAS_NAME, ContasView.VIEW_NAME, FontAwesome.BANK),
		DOMINIOS (VIEW_DOMINIOS_NAME, DominiosView.VIEW_NAME, FontAwesome.GLOBE);
		
		private MessageKey name;
		private String viewName;
		private Resource icon;
		
		private Itens(MessageKey name, String viewName, Resource icon) {
			this.name = name;
			this.viewName = viewName;
			this.icon = icon;
		}

		public String getName() {
			return getMessage(name);
		}

		public String getViewName() {
			return viewName;
		}

		public Resource getIcon() {
			return icon;
		}
	}
	
	/**
	 * Classe que representa um item do menu.
	 */
	private static class MenuItem extends Button implements ClickListener {
		private static final long serialVersionUID = 6429050149434766079L;

		private Itens item;

		public MenuItem(Itens item) {
			this.item = item;
			setPrimaryStyleName(ValoTheme.MENU_ITEM);
			setCaption(item.getName());
			setIcon(item.getIcon());
			addClickListener(this);
		}
		
		public void setSelected() {
			addStyleName(FinanceiroTheme.STATUS_SELECTED);
		}
		
		public void setUnselected() {
			removeStyleName(FinanceiroTheme.STATUS_SELECTED);
		}

		@Override
		public void buttonClick(ClickEvent event) {
			UI.getCurrent().getNavigator().navigateTo(item.getViewName());
		}
	}
	
	/**
	 * Observa o evento de mudança da visão.
	 * 
	 * @param event Evento de mudança da visão
	 */
	public void beforeViewChangeEvent(@Observes @Before ViewChangeEvent event) {
		for (Entry<Itens, MenuItem> entry : menuItens.entrySet()) {
			if (entry.getKey().getViewName().equals(event.getViewName())) {
				entry.getValue().setSelected();
			} else {				
				entry.getValue().setUnselected();
			}
		}
	}
}
