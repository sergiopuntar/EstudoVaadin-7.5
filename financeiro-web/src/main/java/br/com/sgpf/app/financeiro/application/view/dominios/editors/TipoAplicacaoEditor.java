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
import br.com.sgpf.app.financeiro.domain.entity.TipoAplicacao;
import br.com.sgpf.app.financeiro.domain.service.TipoAplicacaoService;
import br.com.sgpf.common.application.component.CustomTextField;
import br.com.sgpf.common.infra.resources.Constants;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import com.vaadin.ui.Field;

/**
 * Classe do editor de Tipos de Aplicação.
 */
@Dependent
public class TipoAplicacaoEditor extends DominioEditor<TipoAplicacao> {
	private static final long serialVersionUID = 3810191266049742763L;
	
	@Inject
	private TipoAplicacaoService tipoAplicacaoService;
	
	@PostConstruct
	public void postConstruct() {
		buildDefaultContent();
	}

	@Override
	protected Collection<String> getColumnPropertyIds() {
		return Lists.newArrayList(PropertyIds.NOME);
	}

	@Override
	protected Collection<TipoAplicacao> findInstances() {
		return tipoAplicacaoService.buscarTiposAplicacao();
	}

	@Override
	protected LinkedHashMap<String, Field<?>> getFormFields() {
		LinkedHashMap<String, Field<?>> fields = Maps.newLinkedHashMap();
		fields.put(PropertyIds.NOME, new CustomTextField(getMessage(FIELD_NOME_LABEL), 20, Unit.EM));
		
		return fields;
	}

	@Override
	protected TipoAplicacao getNewInstance() {
		return new TipoAplicacao();
	}

	@Override
	protected void saveInstance(TipoAplicacao instance) {
		tipoAplicacaoService.salvarTipoAplicacao(instance);
	}

	@Override
	protected void removeInstance(TipoAplicacao instance) {
		tipoAplicacaoService.removerTipoAplicacao(instance);
	}

	@Override
	protected String getInstanceDescription(TipoAplicacao instance) {
		return instance.getNome() != null ? instance.getNome() : Constants.STR_EMPTY;
	}
}
