package br.com.sgpf.app.financeiro.application.view.contas;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_LINK_ADICIONAR_CONTA;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_NAME;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.application.FinanceiroTheme;
import br.com.sgpf.app.financeiro.application.component.ViewHeader;
import br.com.sgpf.app.financeiro.application.view.BaseView;
import br.com.sgpf.app.financeiro.domain.entity.Conta;
import br.com.sgpf.app.financeiro.domain.service.ContaService;
import br.com.sgpf.app.financeiro.domain.valueobject.conta.SaldoConta;
import br.com.sgpf.common.infra.event.Remove;
import br.com.sgpf.common.infra.event.Save;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;

/**
 * Visão de gestão de contas.
 */
@CDIView(ContasView.VIEW_NAME)
public class ContasView extends BaseView {
	private static final long serialVersionUID = 3054590536283345618L;

	public static final String VIEW_NAME = "contas";
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private CadastroContaWindow cadastroContaWindow;
	
	@Inject
	private Instance<ExtratoConta> extratoContaInstance;
	
	private Button adicionarConta;
	
	private HorizontalLayout contasContainer;
	private SaldoContaCard selectedSaldoContaCard;
	private List<SaldoConta> saldosContas;
	
	private Layout extratoContainer;
	
	public ContasView() {
		super(VIEW_NAME);
	}
	
	@PostConstruct
	public void postConstruct() {
		saldosContas = contaService.buscarSaldosMaisRecentes();
		addComponents(buildHeader(), buildContasContainer(), buildExtratoContainer());
		setExpandRatio(extratoContainer, 1);
	}

	/**
	 * Constrói o cabeçalho da visão
	 */
	private Component buildHeader() {
		ViewHeader viewHeader = new ViewHeader(getMessage(VIEW_CONTAS_NAME));
		viewHeader.addComponent(buildAdicionarContaLink());
		viewHeader.setComponentAlignment(adicionarConta, Alignment.MIDDLE_RIGHT);
		
		return viewHeader;
	}
	
	/**
	 * Constrói o link de adição de nova conta
	 */
	private Component buildAdicionarContaLink() {
		adicionarConta = new Button();
		adicionarConta.addStyleName(FinanceiroTheme.ADICIONAR_CONTA);
		adicionarConta.addStyleName(FinanceiroTheme.LINK);
		adicionarConta.setCaption(getMessage(VIEW_CONTAS_LINK_ADICIONAR_CONTA));
		adicionarConta.setIcon(FontAwesome.PLUS);
		
		adicionarConta.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1424225487145742387L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				cadastroContaWindow.setInstance(new Conta());
				UI.getCurrent().addWindow(cadastroContaWindow);
			}
		});
		
		return adicionarConta;
	}

	/**
	 * Observa eventos de salvamento de contas, afim de adicionar o card de
	 * saldo da mesma.
	 * 
	 * @param conta Conta a removida.
	 */
	public void saveConta(@Observes @Save Conta conta) {
		// TODO Adicionar apenas o card da conta nova e não refazer todos
		Conta previamenteSelecionada = null;
		if (selectedSaldoContaCard != null) {
			unselectSaldoContaCard(selectedSaldoContaCard);
			previamenteSelecionada = selectedSaldoContaCard.getSaldoConta().getConta();
		}
		
		saldosContas = contaService.buscarSaldosMaisRecentes();
		buildSaldoContaCards();
		
		SaldoContaCard saldoContaCard = getSaldoContaCard(previamenteSelecionada);
		if (saldoContaCard != null) {
			selectSaldoContaCard(saldoContaCard);
		}
	}
	
	/**
	 * Observa eventos de remoção de contas, afim de remover o card de saldo da
	 * mesma.
	 * 
	 * @param conta Conta a removida.
	 */
	public void removeConta(@Observes @Remove Conta conta) {
		SaldoContaCard saldoContaCard = getSaldoContaCard(conta);
		
		if (saldoContaCard != null) {
			unselectSaldoContaCard(saldoContaCard);
			contasContainer.removeComponent(saldoContaCard);
		}
	}

	/**
	 * Recupera o card de saldo de uma determinada conta.
	 * 
	 * @param conta Conta cujo card deve ser recuperado
	 * @return Card de saldo da conta
	 */
	private SaldoContaCard getSaldoContaCard(Conta conta) {
		for (int i = 0 ; i < contasContainer.getComponentCount() ; i++) {
			SaldoContaCard saldoContaCard = ((SaldoContaCard) contasContainer.getComponent(i));
			
			if (saldoContaCard.getSaldoConta().getConta().equals(conta)) {
				return saldoContaCard;
			}
		}
		
		return null;
	}
	
	/**
	 * Constrói o container para apresentação das contas disponíveis.
	 */
	private Layout buildContasContainer() {
		contasContainer = new HorizontalLayout();
		contasContainer.setMargin(new MarginInfo(false, true, true, true));
		contasContainer.setSpacing(true);
		
		buildSaldoContaCards();
		
		return contasContainer;
	}

	/**
	 * Constrói os cards de saldo das contas.
	 */
	private void buildSaldoContaCards() {
		contasContainer.removeAllComponents();
		
		for (SaldoConta saldoConta : saldosContas) {
			contasContainer.addComponent(buildSaldoContaCard(saldoConta));
		}
	}

	/**
	 * Constrói um card de saldo de conta.
	 * 
	 * @param saldoConta Dados do saldo da conta
	 * @return Card construído
	 */
	private SaldoContaCard buildSaldoContaCard(SaldoConta saldoConta) {
		SaldoContaCard saldoContaCard = new SaldoContaCard(saldoConta);
		
		saldoContaCard.addLayoutClickListener(new LayoutClickListener() {
			private static final long serialVersionUID = -7943144533244383414L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				selectSaldoContaCard((SaldoContaCard) event.getComponent());
			}
		});
		return saldoContaCard;
	}
	
	/**
	 * Ação de seleção de um card de uma conta.
	 * 
	 * @param saldoContaCard Card de conta selecionado
	 */
	private void selectSaldoContaCard(SaldoContaCard saldoContaCard) {
		if(saldoContaCard.equals(selectedSaldoContaCard)) {
			unselectSaldoContaCard(saldoContaCard);
			return;
		}
		
		selectedSaldoContaCard = saldoContaCard;
		
		for (int i = 0 ; i < contasContainer.getComponentCount() ; i++) {
			((SaldoContaCard) contasContainer.getComponent(i)).unselect();
		}
		
		selectedSaldoContaCard.select();
		
		ExtratoConta extratoConta = extratoContaInstance.get();
		extratoConta.buildContent(saldoContaCard);
		
		extratoContainer.removeAllComponents();
		extratoContainer.addComponent(extratoConta);
	}
	
	/**
	 * Ação de deseleção de um card de uma conta. Se o card informado não for
	 * equivalente ao card selecionado, nada é feito.
	 * 
	 * @param saldoContaCard Card de conta deselecionado
	 */
	private void unselectSaldoContaCard(SaldoContaCard saldoContaCard) {
		if (!saldoContaCard.equals(selectedSaldoContaCard)) {
			return;
		}
		
		selectedSaldoContaCard.unselect();
		selectedSaldoContaCard = null;
		extratoContainer.removeAllComponents();
	}
	
	/**
	 * Constrói o container para apresentação do extrato da conta selecionada.
	 */
	private Layout buildExtratoContainer() {
		extratoContainer = new CssLayout();
		extratoContainer.setSizeFull();
		extratoContainer.setPrimaryStyleName(FinanceiroTheme.STRUCT_EXTRATO_CONTA);
		
		return extratoContainer;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
}
