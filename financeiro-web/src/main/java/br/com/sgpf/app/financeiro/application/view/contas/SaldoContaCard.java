package br.com.sgpf.app.financeiro.application.view.contas;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_LABEL_AGENCIA;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_LABEL_CONTA_CORRENTE;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_LABEL_SALDO_DISPONIVEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_LABEL_SALDO_MES;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import java.math.BigDecimal;

import br.com.sgpf.app.financeiro.application.FinanceiroTheme;
import br.com.sgpf.app.financeiro.domain.valueobject.conta.SaldoConta;
import br.com.sgpf.common.application.component.LabelPair;
import br.com.sgpf.common.infra.resources.Constants;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Componente que representa um cartão com o saldo de uma conta
 */
public class SaldoContaCard extends VerticalLayout {
	private static final long serialVersionUID = -3396351558911681192L;
	
	private static final String STYLE_NOME_BANCO = "nome-banco";
	private static final String STYLE_NUMERO_AGENCIA = "numero-agencia";
	private static final String STYLE_NUMERO_CONTA = "numero-conta";
	
	private SaldoConta saldoConta;

	private LabelPair agencia;

	private LabelPair conta;

	private LabelPair saldoMes;

	private LabelPair saldoDisponivel;
	
	/**
	 * Constrói o cartão do saldo da conta.
	 * 
	 * @param saldoConta Dados do saldo da conta
	 */
	public SaldoContaCard(SaldoConta saldoConta) {
		this.saldoConta = saldoConta;
		addStyleName(ValoTheme.LAYOUT_CARD);
		addStyleName(FinanceiroTheme.COMP_SALDO_CONTA_CARD);
		buildContent();
	}

	/**
	 * Constrói o conteúdo do card.
	 */
	private void buildContent() {
		Label banco = new Label(saldoConta.getConta().getBanco().getNome());
		banco.addStyleName(STYLE_NOME_BANCO);
		banco.addStyleName(ValoTheme.LABEL_BOLD);
		banco.addStyleName(ValoTheme.LABEL_COLORED);
		
		agencia = new LabelPair(getMessage(VIEW_CONTAS_LABEL_AGENCIA), saldoConta.getConta().getNumeroAgencia());
		agencia.addRightStyleNames(STYLE_NUMERO_AGENCIA);
		
		conta = new LabelPair(getMessage(VIEW_CONTAS_LABEL_CONTA_CORRENTE), saldoConta.getConta().getNumeroConta());
		conta.addRightStyleNames(STYLE_NUMERO_CONTA);
		
		saldoMes = new LabelPair();
		updateLabelSaldoMes();
		
		saldoDisponivel = new LabelPair();
		updateLabelSaldoDisponivel();
		
		addComponents(banco, agencia, conta, saldoMes, saldoDisponivel);
	}
	
	/**
	 * Atualiza o conteúdo da label de saldo do mês.
	 */
	private void updateLabelSaldoMes() {
		if (saldoConta.getAno() != null && saldoConta.getMes() != null && saldoConta.getSaldoMes() != null) {
			saldoMes.setLeftContent(getMessage(VIEW_CONTAS_LABEL_SALDO_MES, saldoConta.getAno(), saldoConta.getMes().ordinal() + 1));
			saldoMes.setRightProperty(new ObjectProperty<BigDecimal>(saldoConta.getSaldoMes(), BigDecimal.class));
			saldoMes.setRightStyleName(FinanceiroTheme.getMonetaryStyle(saldoConta.getSaldoMes()));
		} else {
			saldoMes.setLeftContent(Constants.STR_EMPTY);
			saldoMes.setRightContent(Constants.STR_EMPTY);
			saldoMes.setRightStyleName(Constants.STR_EMPTY);
		}
	}
	
	/**
	 * Atualiza o conteúdo da label de saldo disponível.
	 */
	private void updateLabelSaldoDisponivel() {
		if (saldoConta.getSaldoDisponivel() != null) {
			saldoDisponivel.setLeftContent(getMessage(VIEW_CONTAS_LABEL_SALDO_DISPONIVEL));
			saldoDisponivel.setRightProperty(new ObjectProperty<BigDecimal>(saldoConta.getSaldoDisponivel(), BigDecimal.class));
			saldoDisponivel.setRightStyleName(FinanceiroTheme.getMonetaryStyle(saldoConta.getSaldoDisponivel()));
		} else {
			saldoDisponivel.setLeftContent(Constants.STR_EMPTY);
			saldoDisponivel.setRightContent(Constants.STR_EMPTY);
			saldoDisponivel.setRightStyleName(Constants.STR_EMPTY);
		}
	}

	/**
	 * Recupera o saldo da conta apresentado no card.
	 * 
	 * @return Saldo da conta apresentado no card
	 */
	public SaldoConta getSaldoConta() {
		return saldoConta;
	}
	
	/**
	 * Altera o card para o estado selecionado.
	 */
	public void select() {
		addStyleName(ValoTheme.PANEL_WELL);
		removeStyleName(ValoTheme.LAYOUT_CARD);
	}
	
	/**
	 * Altera o card para o estado não selecionado.
	 */
	public void unselect() {
		removeStyleName(ValoTheme.PANEL_WELL);
		addStyleName(ValoTheme.LAYOUT_CARD);
	}
}
