package br.com.sgpf.app.financeiro.application;

import java.math.BigDecimal;

import br.com.sgpf.common.infra.resources.Constants;

import com.vaadin.ui.themes.ValoTheme;

/**
 * Classes para o tema Financeiro.
 */
public class FinanceiroTheme {

	public static final String COMP_LABEL_PAIR = "label-pair";
	
	public static final String COMP_SALDO_CONTA_CARD = "saldo-conta-card";
	
	public static final String STRUCT_MAIN_LAYOUT = "main-layout";
	
	public static final String STRUCT_VIEW_CONTENT = "view-content";
	
	public static final String STRUCT_DOMINIO_EDITOR = "dominio-editor";
	
	public static final String STRUCT_EXTRATO_CONTA = "extrato-conta";
	
	public static final String ADICIONAR_CONTA = "adicionar-conta";
	
	public static final String LOGO = "logo";
	
	public static final String LINK = "link";
	
	public static final String STATUS_SELECTED = "selected";
	
	public static final String STATUS_POSITIVE = "positive";
	
	public static final String STATUS_NEGATIVE = "negative";


	/**
	 * Recupera o estilo a ser aplicado a um valor monetário.
	 * 
	 * @param value Valor monetário
	 * @return Estilo para o valor
	 */
	public static String getMonetaryStyle(BigDecimal value) {
		if (value == null) {
			return Constants.STR_EMPTY;
		}
		
		int result = value.compareTo(BigDecimal.ZERO);
		return result < 0 ? STATUS_NEGATIVE : result > 0 ? STATUS_POSITIVE : ValoTheme.LABEL_COLORED;
	}
}
