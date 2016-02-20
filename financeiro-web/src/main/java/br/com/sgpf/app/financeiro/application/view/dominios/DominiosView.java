package br.com.sgpf.app.financeiro.application.view.dominios;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.DOMAIN_BANCO_NAME;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.DOMAIN_GRUPO_OPERACAO_NAME;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.DOMAIN_TIPO_APLICACAO_NAME;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NAME;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.application.FinanceiroTheme;
import br.com.sgpf.app.financeiro.application.PropertyIds;
import br.com.sgpf.app.financeiro.application.component.ViewHeader;
import br.com.sgpf.app.financeiro.application.view.BaseView;
import br.com.sgpf.app.financeiro.application.view.dominios.editors.BancoEditor;
import br.com.sgpf.app.financeiro.application.view.dominios.editors.GrupoOperacaoEditor;
import br.com.sgpf.app.financeiro.application.view.dominios.editors.TipoAplicacaoEditor;
import br.com.sgpf.common.infra.i18n.MessageKey;
import br.com.sgpf.common.infra.resources.Constants;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Layout;

/**
 * Visão de gestão dos domínios utilizados pelo sistema.
 */
@CDIView(DominiosView.VIEW_NAME)
public class DominiosView extends BaseView {
	private static final long serialVersionUID = 706751956855936900L;
	
	public static final String VIEW_NAME = "dominios";
	
	@Inject @Any
	private Instance<DominioEditor<?>> dominioEditor;
	
	private ComboBox dominioSelect;
	
	private Layout editorContainer;
	
	public DominiosView() {
		super(VIEW_NAME);
	}
	
	@PostConstruct
	public void postConstruct() {
		addComponents(buildHeader(), buildEditorContainer());
		setExpandRatio(editorContainer, 1);
	}

	/**
	 * Constrói o cabeçalho da visão
	 */
	private Component buildHeader() {
		ViewHeader viewHeader = new ViewHeader(getMessage(VIEW_DOMINIOS_NAME));
		viewHeader.addComponent(buildDominioSelect());
		viewHeader.setComponentAlignment(dominioSelect, Alignment.MIDDLE_RIGHT);
		
		return viewHeader;
	}
	
	/**
	 * Constrói o seletor de domínios.
	 */
	private ComboBox buildDominioSelect() {
		dominioSelect = new ComboBox();
		dominioSelect.setItemCaptionPropertyId(PropertyIds.NAME);
		dominioSelect.setWidth(20, Unit.EM);
		dominioSelect.setContainerDataSource(new BeanItemContainer<Dominios>(Dominios.class, Lists.newArrayList(Dominios.values())));
		dominioSelect.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 8737855498665597024L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				editorContainer.removeAllComponents();
				
				Dominios dominio = (Dominios) event.getProperty().getValue();
				if (dominio != null) {
					DominioEditor<?> editor = dominioEditor.select(dominio.getEditorClass()).get();
					editor.setStyleName(dominio.name().toLowerCase().replace(Constants.STR_UNDERLINE, Constants.STR_DASH));
					editorContainer.addComponent(editor);
				}
			}
		});
		
		return dominioSelect;
	}
	
	/**
	 * Constrói o container para apresentação do editor do domínio selecionado.
	 */
	private Layout buildEditorContainer() {
		editorContainer = new CssLayout();
		editorContainer.setSizeFull();
		editorContainer.setPrimaryStyleName(FinanceiroTheme.STRUCT_DOMINIO_EDITOR);
		
		return editorContainer;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
	
	/**
	 * Enumeração de domínios disponíveis.
	 */
	public static enum Dominios {
		BANCO (DOMAIN_BANCO_NAME, BancoEditor.class),
		GRUPO_OPERACAO (DOMAIN_GRUPO_OPERACAO_NAME, GrupoOperacaoEditor.class),
		TIPO_APLICACAO (DOMAIN_TIPO_APLICACAO_NAME, TipoAplicacaoEditor.class);
		
		private MessageKey name;
		private Class<? extends DominioEditor<?>> editorClass;

		private Dominios(MessageKey name, Class<? extends DominioEditor<?>> editorClass) {
			this.name = name;
			this.editorClass = editorClass;
		}
		
		public String getName() {
			return getMessage(name);
		}

		public Class<? extends DominioEditor<?>> getEditorClass() {
			return editorClass;
		}
	}
}
