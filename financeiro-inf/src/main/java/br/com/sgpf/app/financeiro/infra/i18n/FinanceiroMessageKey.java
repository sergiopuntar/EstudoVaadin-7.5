package br.com.sgpf.app.financeiro.infra.i18n;

import br.com.sgpf.common.infra.i18n.MessageKey;

/**
 * Enumeração de chaves de mensagens da aplicação.
 */
public enum FinanceiroMessageKey implements MessageKey {
	
	// View dashboard
	VIEW_DASHBOARD_NAME ("view.dashboard.name"),
	
	// View contas
	VIEW_CONTAS_NAME ("view.contas.name"),
	VIEW_CONTAS_LINK_ADICIONAR_CONTA ("view.contas.link.adicionar_conta"),
	VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_SUCCESS ("view.contas.conta.notification.save.success"),
	VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_VALIDATION ("view.contas.conta.notification.save.fail.validation"),
	VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_PERSISTENCE ("view.contas.conta.notification.save.fail.persistence"),
	VIEW_CONTAS_CONTA_NOTIFICATION_SAVE_FAIL_UNEXPECTED ("view.contas.conta.notification.save.fail.unexpected"),
	VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_SUCCESS ("view.contas.conta.notification.remove.success"),
	VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_FAIL_PERSISTENCE ("view.contas.conta.notification.remove.fail.persistence"),
	VIEW_CONTAS_CONTA_NOTIFICATION_REMOVE_FAIL_UNEXPECTED ("view.contas.conta.notification.remove.fail.unexpected"),
	VIEW_CONTAS_LABEL_AGENCIA ("view.contas.label.agencia"),
	VIEW_CONTAS_LABEL_CONTA_CORRENTE ("view.contas.label.conta.corrente"),
	VIEW_CONTAS_LABEL_SALDO_MES ("view.contas.label.saldo.mes"),
	VIEW_CONTAS_LABEL_SALDO_DISPONIVEL ("view.contas.label.saldo.disponivel"),
	
	// View domain
	VIEW_DOMINIOS_NAME ("view.dominios.name"),
	VIEW_DOMINIOS_NOTIFICATION_SAVE_SUCCESS ("view.dominios.notification.save.success"),
	VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_VALIDATION ("view.dominios.notification.save.fail.validation"),
	VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_PERSISTENCE ("view.dominios.notification.save.fail.persistence"),
	VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_UNEXPECTED ("view.dominios.notification.save.fail.unexpected"),
	VIEW_DOMINIOS_NOTIFICATION_REMOVE_SUCCESS ("view.dominios.notification.remove.success"),
	VIEW_DOMINIOS_NOTIFICATION_REMOVE_FAIL_PERSISTENCE ("view.dominios.notification.remove.fail.persistence"),
	VIEW_DOMINIOS_NOTIFICATION_REMOVE_FAIL_UNEXPECTED ("view.dominios.notification.remove.fail.unexpected"),
	
	// Domínio
	DOMAIN_BANCO_NAME ("domain.banco.name"),
	DOMAIN_GRUPO_OPERACAO_NAME ("domain.grupo_operacao.name"),
	DOMAIN_TIPO_APLICACAO_NAME ("domain.tipo_aplicacao.name"),
	
	// Campos
	FIELD_BANCO_LABEL ("field.banco.label"),
	FIELD_DESCRICAO_LABEL ("field.descricao.label"),
	FIELD_NOME_LABEL ("field.nome.label"),
	FIELD_NUMERO_AGENCIA_LABEL ("field.numero_agencia.label"),
	FIELD_NUMERO_CONTA_LABEL ("field.numero_conta.label"),
	FIELD_SIGLA_LABEL ("field.sigla.label"),
	
	// Botões
	BUTTON_ADD_LABEL ("button.add.label"),
	BUTTON_SAVE_LABEL ("button.save.label"),
	BUTTON_REMOVE_LABEL ("button.remove.label"),
	BUTTON_CANCEL_LABEL ("button.cancel.label");
	
	private String name;

	private FinanceiroMessageKey(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
