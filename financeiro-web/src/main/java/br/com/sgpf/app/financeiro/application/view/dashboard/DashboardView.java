package br.com.sgpf.app.financeiro.application.view.dashboard;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DASHBOARD_NAME;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import javax.annotation.PostConstruct;

import br.com.sgpf.app.financeiro.application.component.ViewHeader;
import br.com.sgpf.app.financeiro.application.view.BaseView;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

/**
 * Visão inicial do sistema. Apresenta um resumo das informações financeiras.
 */
@CDIView(DashboardView.VIEW_NAME)
public class DashboardView extends BaseView {
	private static final long serialVersionUID = -1370987278295682113L;
	
	public static final String VIEW_NAME = "";

	public DashboardView() {
		super(VIEW_NAME);
	}

	@PostConstruct
	public void postConstruct() {
		addComponents(new ViewHeader(getMessage(VIEW_DASHBOARD_NAME)));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
}
