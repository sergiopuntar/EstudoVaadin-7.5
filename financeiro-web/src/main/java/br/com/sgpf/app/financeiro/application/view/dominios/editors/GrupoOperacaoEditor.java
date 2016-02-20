package br.com.sgpf.app.financeiro.application.view.dominios.editors;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.FIELD_NOME_LABEL;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import java.util.Collection;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.application.PropertyIds;
import br.com.sgpf.app.financeiro.application.view.dominios.DominioEditor;
import br.com.sgpf.app.financeiro.domain.entity.GrupoOperacao;
import br.com.sgpf.app.financeiro.domain.service.GrupoOperacaoService;
import br.com.sgpf.common.application.component.CustomTextField;
import br.com.sgpf.common.infra.resources.Constants;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import com.vaadin.ui.Field;

/**
 * Classe do editor de Grupos de Operação.
 */
@Dependent
public class GrupoOperacaoEditor extends DominioEditor<GrupoOperacao> {
	private static final long serialVersionUID = 3810191266049742763L;
	
	@Inject
	private GrupoOperacaoService grupoOperacaoService;
	
	@PostConstruct
	public void postConstruct() {
		buildDefaultContent();
	}

	@Override
	protected Collection<String> getColumnPropertyIds() {
		return Lists.newArrayList(PropertyIds.NOME);
	}

	@Override
	protected Collection<GrupoOperacao> findInstances() {
		return grupoOperacaoService.buscarGruposOperacao();
	}

	@Override
	protected LinkedHashMap<String, Field<?>> getFormFields() {
		LinkedHashMap<String, Field<?>> fields = Maps.newLinkedHashMap();
		fields.put(PropertyIds.NOME, new CustomTextField(getMessage(FIELD_NOME_LABEL), 20, Unit.EM));
		
		return fields;
	}

	@Override
	protected GrupoOperacao getNewInstance() {
		return new GrupoOperacao();
	}

	@Override
	protected void saveInstance(GrupoOperacao instance) {
		grupoOperacaoService.salvarGrupoOperacao(instance);
	}

	@Override
	protected void removeInstance(GrupoOperacao instance) {
		grupoOperacaoService.removerGrupoOperacao(instance);
	}

	@Override
	protected String getInstanceDescription(GrupoOperacao instance) {
		return instance.getNome() != null ? instance.getNome() : Constants.STR_EMPTY;
	}
}
