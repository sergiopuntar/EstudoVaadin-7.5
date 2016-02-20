package br.com.sgpf.app.financeiro.application.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Cabeçalho padrão das visões do sistema
 */
public class ViewHeader extends HorizontalLayout {
	private static final long serialVersionUID = -2920443139980420804L;
	
	private String title;
	
	/**
	 * Constrói um cabeçalho padrão para visão.
	 *  
	 * @param title Título da visão
	 */
	public ViewHeader(String title) {
		super();
		this.title = title;
		buildContent();
	}

	/**
	 * Constrói o conteúdo do cabeçalho.
	 */
	void buildContent() {
		setMargin(true);
		setWidth(100, Unit.PERCENTAGE);
		
		Label label = new Label(title);
		label.addStyleName(ValoTheme.LABEL_H1);
		label.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		
		addComponent(label);
		setComponentAlignment(label, Alignment.MIDDLE_LEFT);
	}
}
