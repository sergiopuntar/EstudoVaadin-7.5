package br.com.sgpf.common.application.component;

import br.com.sgpf.app.financeiro.application.FinanceiroTheme;

import com.vaadin.data.Property;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Componente que representa uma par de labels.
 */
public class LabelPair extends HorizontalLayout {
	private static final long serialVersionUID = 8680360240541154003L;
	
	private Label left;
	private Label right;

	/**
	 * Constrói um par de labels.
	 * 
	 * @param left Label esquerda
	 * @param right Label direita
	 */
	public LabelPair(Label left, Label right) {
		this.left = left;
		this.right = right;
		setSpacing(true);
		addStyleName(FinanceiroTheme.COMP_LABEL_PAIR);
		addComponents(left, right);
	}
	
	/**
	 * Constrói um par de labels sem conteúdo.
	 */
	public LabelPair() {
		this(new Label(), new Label());
	}
	
	/**
	 * Constrói um par de labels.
	 * 
	 * @param left Conteúdo da label esquerda
	 * @param right Conteúdo da label direita
	 */
	public LabelPair(String left, String right) {
		this(new Label(left), new Label(right));
	}
	
	/**
	 * Constrói um par de labels.
	 * 
	 * @param left Propriedade com o conteúdo da label esquerda
	 * @param right Propriedade com o conteúdo da label direita
	 */
	public LabelPair(Property<?> left, Property<?> right) {
		this(new Label(left), new Label(right));
	}
	
	/**
	 * Constrói um par de labels.
	 * 
	 * @param left Conteúdo da label esquerda
	 * @param right Propriedade com o conteúdo da label direita
	 */
	public LabelPair(String left, Property<?> right) {
		this(new Label(left), new Label(right));
	}
	
	/**
	 * Constrói um par de labels.
	 * 
	 * @param left Propriedade com o conteúdo da label esquerda
	 * @param right Conteúdo da label direita
	 */
	public LabelPair(Property<?> left, String right) {
		this(new Label(left), new Label(right));
	}
	
	/**
	 * Define o conteúdo da label esquerda
	 * 
	 * @param leftContent Conteúdo da label esquerda
	 */
	public void setLeftContent(String leftContent) {
		left.setValue(leftContent);
	}
	
	/**
	 * Define a propriedade com o conteúdo da label esquerda
	 * 
	 * @param leftProperty Propriedade com o conteúdo da label esquerda
	 */
	public void setLeftProperty(Property<?> leftProperty) {
		left.setPropertyDataSource(leftProperty);
	}
	
	/**
	 * Define o conteúdo da label direita
	 * 
	 * @param rightContent Conteúdo da label direita
	 */
	public void setRightContent(String rightContent) {
		right.setValue(rightContent);
	}
	
	/**
	 * Define a propriedade com o conteúdo da label direita
	 * 
	 * @param rightProperty Propriedade com o conteúdo da label direita
	 */
	public void setRightProperty(Property<?> rightProperty) {
		right.setPropertyDataSource(rightProperty);
	}
	
	/**
	 * Define o estilo da label esquerda.
	 * 
	 * @param styleName Nome do estilo
	 */
	public void setLeftStyleName(String styleName) {
		left.setStyleName(styleName);
	}
	
	/**
	 * Adiciona estilos à label esquerda.
	 * 
	 * @param styleNames Nomes dos estilos
	 */
	public void addLeftStyleNames(String... styleNames) {
		addStyleNames(left, styleNames);
	}
	
	/**
	 * Define o estilo da label direita.
	 * 
	 * @param styleName Nome do estilo
	 */
	public void setRightStyleName(String styleName) {
		right.setStyleName(styleName);
	}
	
	/**
	 * Adiciona estilos à label direita.
	 * 
	 * @param styleNames Nomes dos estilos
	 */
	public void addRightStyleNames(String... styleNames) {
		addStyleNames(right, styleNames);
	}
	
	/**
	 * Adiciona estilos à uma label.
	 * 
	 * @param label Label que receberá os estilos
	 * @param styleNames Nomes dos estilos
	 */
	public void addStyleNames(Label label, String... styleNames) {
		for (String styleName : styleNames) {
			label.addStyleName(styleName);
		}
	}
}
