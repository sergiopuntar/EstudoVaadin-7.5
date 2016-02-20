package br.com.sgpf.app.financeiro.application.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

/**
 * Classe base para todas as visões da aplicação.
 */
public abstract class BaseView extends VerticalLayout implements View {
	private static final long serialVersionUID = -8183183719120039971L;

	public BaseView(String viewName) {
		setStyleName(viewName);
		setHeight(100, Unit.PERCENTAGE);
	}
}
