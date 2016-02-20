package br.com.sgpf.app.financeiro.application.view.contas;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_BANCO_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_DESCRICAO_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_NOME_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_NUMERO_AGENCIA_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_NUMERO_CONTA_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_FAIL_PERSISTENCE;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_FAIL_UNEXPECTED;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_SUCCESS;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_PERSISTENCE;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_UNEXPECTED;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_VALIDATION;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_SUCCESS;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sgpf.app.financeiro.application.PropertyIds;
import br.com.sgpf.app.financeiro.domain.entity.Banco;
import br.com.sgpf.app.financeiro.domain.entity.Conta;
import br.com.sgpf.app.financeiro.domain.repository.BancoRepository;
import br.com.sgpf.app.financeiro.domain.service.ContaService;
import br.com.sgpf.common.application.component.BaseFormLayout;
import br.com.sgpf.common.application.component.CustomTextArea;
import br.com.sgpf.common.application.component.CustomTextField;
import br.com.sgpf.common.application.notification.ApplicationNotification;
import br.com.sgpf.common.infra.event.Remove;
import br.com.sgpf.common.infra.event.Save;

import com.vaadin.cdi.UIScoped;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ClientConnector.AttachListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Janela de cadastro de conta.
 */
@UIScoped
public class CadastroContaWindow extends Window implements AttachListener {
	private static final long serialVersionUID = 8994345769010640031L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CadastroContaWindow.class);
	
	private static final String ERROR_VALIDACAO = "Erro de validação de conta";
	
	private static final String ERROR_PERSISTENCIA = "Erro de persistência de conta";
	
	private static final String ERROR_INESPERADO_PERSISTENCIA = "Erro inesperado durante persistência de conta";
	
	private static final String ERROR_REMOCAOO = "Erro de remoção de conta";
	
	private static final String ERROR_INESPERADO_REMOCAO = "Erro inesperado durante remoção de conta";
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private BancoRepository bancoRepository;
	
	@Inject @Save
	private javax.enterprise.event.Event<Conta> saveConta;

	@Inject @Remove
	private javax.enterprise.event.Event<Conta> removeConta;
	
	private ContaForm form;
	
	private BeanFieldGroup<Conta> formFields;
	
	public CadastroContaWindow() {
		setClosable(false);
		setModal(true);
		setDraggable(false);
		setResizable(false);
		addAttachListener(this);
		
		form = new ContaForm();
		
		formFields = new BeanFieldGroup<Conta>(Conta.class);
		formFields.bindMemberFields(form);
		
		setContent(form);
	}
	
	/**
	 * Classe do formulário de conta.
	 */
	private class ContaForm extends BaseFormLayout {
		private static final long serialVersionUID = 8909375816610826561L;

		private TextField nome;
		private TextArea descricao;
		private ComboBox banco;
		private TextField numeroAgencia;
		private TextField numeroConta;
		
		public ContaForm() {
			super();
			setSizeUndefined();
			nome = new CustomTextField(getMessage(FIELD_NOME_LABEL), 20, Unit.EM);
			descricao = new CustomTextArea(getMessage(FIELD_DESCRICAO_LABEL), 30, Unit.EM);
			banco = buildBancoComboBox();
			numeroAgencia = new CustomTextField(getMessage(FIELD_NUMERO_AGENCIA_LABEL), 10, Unit.EM);
			numeroConta = new CustomTextField(getMessage(FIELD_NUMERO_CONTA_LABEL), 10, Unit.EM);
			buildContent();
		}

		/**
		 * Constrói a combo box de seleção de banco.
		 * 
		 * @return Combo box de seleção de banco
		 */
		private ComboBox buildBancoComboBox() {
			ComboBox banco = new ComboBox(getMessage(FIELD_BANCO_LABEL));
			banco.setWidth(20, Unit.EM);
			banco.setItemCaptionPropertyId(PropertyIds.NOME);
			return banco;
		}

		@Override
		protected Field<?>[] getFields() {
			return new Field[]{nome, descricao, banco, numeroAgencia, numeroConta};
		}

		@Override
		protected void saveAction(ClickEvent event) {
			salvarConta();
		}

		@Override
		protected void removeAction(ClickEvent event) {
			removerConta();
		}

		@Override
		protected void cancelAction(ClickEvent event) {
			close();
		}
	}
	
	/**
	 * Define a instância da conta a ser apresentada no formulário.
	 *  
	 * @param conta Instância da conta a ser apresentada
	 */
	public void setInstance(Conta conta) {
		formFields.setItemDataSource(conta);
		form.getRemove().setVisible(conta.isPersisted());
	}

	/**
	 * Salva os dados da conta.
	 */
	private void salvarConta() {
		try {
			formFields.commit();
		} catch (CommitException e) {
			LOGGER.trace(ERROR_VALIDACAO, e);
			ApplicationNotification.fail(null, getMessage(VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_VALIDATION));
			return;
		}
		
		Conta conta = formFields.getItemDataSource().getBean();
		
		try {
			contaService.salvarConta(conta);
			ApplicationNotification.success(null, getMessage(VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_SUCCESS));
			
			close();
			saveConta.fire(conta);
		} catch (Exception e) {
			if (e.getCause() instanceof PersistenceException) {
				LOGGER.error(ERROR_PERSISTENCIA, e);
				ApplicationNotification.fail(null, getMessage(VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_PERSISTENCE));
			} else {
				LOGGER.error(ERROR_INESPERADO_PERSISTENCIA, e);
				ApplicationNotification.error(null, getMessage(VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_UNEXPECTED));
			}
		}
	}

	/**
	 * Apaga a conta.
	 */
	private void removerConta() {
		Conta conta = formFields.getItemDataSource().getBean();
		
		try {
			contaService.removerConta(conta);
			ApplicationNotification.success(null, getMessage(VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_SUCCESS));
			
			close();
			removeConta.fire(conta);
		} catch (Exception e) {
			if (e.getCause() instanceof PersistenceException) {
				LOGGER.error(ERROR_REMOCAOO, e);
				ApplicationNotification.fail(null, getMessage(VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_FAIL_PERSISTENCE));
			} else {
				LOGGER.error(ERROR_INESPERADO_REMOCAO, e);
				ApplicationNotification.error(null, getMessage(VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_FAIL_UNEXPECTED));
			}
		}
	}

	@Override
	public void attach(AttachEvent event) {
		form.nome.focus();
		form.banco.setContainerDataSource(new BeanItemContainer<Banco>(Banco.class, bancoRepository.findAll()));
	}

	@Override
	public void close() {
		formFields.setItemDataSource((Conta) null);
		super.close();
	}
}
