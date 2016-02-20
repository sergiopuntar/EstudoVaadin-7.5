package br.com.sgpf.app.financeiro.application.view.dominios.editors;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_NOME_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_SIGLA_LABEL;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import java.util.Collection;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.application.PropertyIds;
import br.com.sgpf.app.financeiro.application.view.dominios.DominioEditor;
import br.com.sgpf.app.financeiro.domain.entity.Banco;
import br.com.sgpf.app.financeiro.domain.service.BancoService;
import br.com.sgpf.common.application.component.CustomTextField;
import br.com.sgpf.common.infra.resources.Constants;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import com.vaadin.ui.Field;

/**
 * Classe do editor de Bancos de Relacionamento.
 */
@Dependent
public class BancoEditor extends DominioEditor<Banco> {
	private static final long serialVersionUID = 3810191266049742763L;
	
	@Inject
	private BancoService bancoService;
	
	@PostConstruct
	public void postConstruct() {
		buildDefaultContent();
	}

	@Override
	protected Collection<String> getColumnPropertyIds() {
		return Lists.newArrayList(PropertyIds.SIGLA, PropertyIds.NOME);
	}

	@Override
	protected Collection<Banco> findInstances() {
		return bancoService.buscarBancos();
	}

	@Override
	protected LinkedHashMap<String, Field<?>> getFormFields() {
		LinkedHashMap<String, Field<?>> fields = Maps.newLinkedHashMap();
		fields.put(PropertyIds.SIGLA, new CustomTextField(getMessage(FIELD_SIGLA_LABEL), 5, Unit.EM));
		fields.put(PropertyIds.NOME, new CustomTextField(getMessage(FIELD_NOME_LABEL), 20, Unit.EM));
		
		return fields;
	}

	@Override
	protected Banco getNewInstance() {
		return new Banco();
	}

	@Override
	protected void saveInstance(Banco instance) {
		bancoService.salvarBanco(instance);
	}

	@Override
	protected void removeInstance(Banco instance) {
		bancoService.removerBanco(instance);
	}

	@Override
	protected String getInstanceDescription(Banco instance) {
		return instance.getNome() != null ? instance.getNome() : Constants.STR_EMPTY;
	}
}
