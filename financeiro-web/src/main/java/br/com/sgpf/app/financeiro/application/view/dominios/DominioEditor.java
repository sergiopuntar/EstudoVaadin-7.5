package br.com.sgpf.app.financeiro.application.view.dominios;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.BUTTON_ADD_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NOTIFICATION_REMOVE_FAIL_PERSISTENCE;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NOTIFICATION_REMOVE_FAIL_UNEXPECTED;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NOTIFICATION_REMOVE_SUCCESS;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_PERSISTENCE;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_UNEXPECTED;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_VALIDATION;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.VIEW_DOMINIOS_NOTIFICATION_SAVE_SUCCESS;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sgpf.common.application.component.BaseFormLayout;
import br.com.sgpf.common.application.notification.ApplicationNotification;
import br.com.sgpf.common.domain.entity.Entity;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Classe abstrata dos editores de domínios.
 * 
 * @param <T> Tipo da entidade do domínio.
 */
public abstract class DominioEditor<T extends Entity<?>> extends VerticalLayout {
	private static final long serialVersionUID = -9092053546998054270L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DominioEditor.class);
	
	private static final String ERROR_VALIDACAO_INSTANCIA = "Erro de validação de instância de domínio";
	
	private static final String ERROR_PERSISTENCIA_INSTANCIA = "Erro de persistência de instância de domínio";
	
	private static final String ERROR_INESPERADO_PERSISTENCIA_INSTANCIA = "Erro inesperado durante persistência de instância de domínio";
	
	private static final String ERROR_REMOCAOO_INSTANCIA = "Erro de remoção de instância de domínio";
	
	private static final String ERROR_INESPERADO_REMOCAO_INSTANCIA = "Erro inesperado durante remoção de instância de domínio";

	private Class<T> clazz;
	
	private CssLayout filterForm;
	
	private Button newInstance;
	
	private Grid instanceList;
	
	private DominioForm instanceForm;
	
	private BeanFieldGroup<T> formFields;

	@SuppressWarnings("unchecked")
	public DominioEditor() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];		
		formFields = new BeanFieldGroup<>(clazz);
		setMargin(new MarginInfo(false, true, true, true));
		setHeight(100, Unit.PERCENTAGE);
	}
	
	/**
	 * Constrói o conteúdo padrão de um editor de domínios, composta pela lista
	 * de instancias e pelo formulário de edição.
	 */
	protected void buildDefaultContent() {
		HorizontalLayout toolbar = new HorizontalLayout();
		toolbar.setWidth(100, Unit.PERCENTAGE);
		toolbar.addComponents(buildFilterForm(), buildNewInstaceButton());
		toolbar.setComponentAlignment(toolbar.getComponent(0), Alignment.MIDDLE_LEFT);
		toolbar.setComponentAlignment(toolbar.getComponent(1), Alignment.MIDDLE_RIGHT);
		toolbar.setExpandRatio(toolbar.getComponent(0), 8);
		toolbar.setExpandRatio(toolbar.getComponent(1), 2);
		
		HorizontalLayout domainContent = new HorizontalLayout();
		domainContent.setWidth(100, Unit.PERCENTAGE);
		domainContent.addComponent(buildInstanceList());
		domainContent.addComponent(new CssLayout(buildInstanceForm()));
		domainContent.setExpandRatio(domainContent.getComponent(0), 3);
		domainContent.setExpandRatio(domainContent.getComponent(1), 2);
		
		addComponents(toolbar, domainContent);
		setExpandRatio(domainContent, 1);
		
		refreshInstances();
	}

	/**
	 * Constrói o formulário de filtragem da lista de instâncias.
	 */
	private CssLayout buildFilterForm() {
		filterForm = new CssLayout();
		
		// TODO construir conteúdo do filtro
		
		if (filterForm.getComponentCount() == 0) {
			filterForm.setVisible(false);
		}
		
		return filterForm;
	}

	/**
	 * Constrói o botão de criação de nova instância;
	 * 
	 * @return Botão de criação de nova instância
	 */
	private Component buildNewInstaceButton() {
		newInstance = new Button(getMessage(BUTTON_ADD_LABEL));
		newInstance.setStyleName(ValoTheme.BUTTON_PRIMARY);
		newInstance.setIcon(FontAwesome.PLUS);
		newInstance.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 6771622959378154670L;

			@Override
			public void buttonClick(ClickEvent event) {
				createInstance();
			}
		});
		
		return newInstance;
	}

	/**
	 * Constrói a lista de instâncias.
	 */
	private Grid buildInstanceList() {
		instanceList = new Grid();
		instanceList.setWidth(100, Unit.PERCENTAGE);
		instanceList.setContainerDataSource(new BeanItemContainer<T>(clazz));
		instanceList.setColumns(getColumnPropertyIds().toArray());
		instanceList.setSelectionMode(Grid.SelectionMode.SINGLE);
		instanceList.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 91043053993861871L;

			@Override
			@SuppressWarnings("unchecked")
			public void select(SelectionEvent event) {
				if (instanceList.getSelectedRow() != null) {
					editInstance((T) instanceList.getSelectedRow());
				}
			}
		});
		
		return instanceList;
	}

	/**
	 * Recupera a lista de identificadores das propriedades das colunas da
	 * lista de instâncias. No caso, os identificadores equivalem aos nomes das
	 * propriedades no bean.
	 *  
	 * @return Lista dos identificadores das colunas
	 */
	protected abstract Collection<String> getColumnPropertyIds();
	
	/**
	 * Recarrega o conteúdo da lista de instâncias. 
	 */
	protected void refreshInstances() {
		instanceList.setContainerDataSource(new BeanItemContainer<T>(clazz, findInstances()));
	}

	/**
	 * Recupera as instâncias do domínio.
	 *  
	 * @return Instâncias do domínio
	 */
	protected abstract Collection<T> findInstances();
	
	/**
	 * Constrói o formulário de edição da instância.
	 */
	private FormLayout buildInstanceForm() {
		LinkedHashMap<String, Field<?>> fields = getFormFields();
		
		instanceForm = new DominioForm(fields.values());
		instanceForm.setVisible(false);
		
		for (Entry<String, Field<?>> entry : fields.entrySet()) {
			formFields.bind(entry.getValue(), entry.getKey());
		}
		
		return instanceForm;
	}
	
	/**
	 * Classe do formulário de domínio.
	 */
	private class DominioForm extends BaseFormLayout {
		private static final long serialVersionUID = -5613951119179330553L;
		
		private Collection<Field<?>> fields; 
		
		public DominioForm(Collection<Field<?>> fields) {
			super();
			this.fields = fields;
			buildContent();
		}

		@Override
		protected Field<?>[] getFields() {
			return fields.toArray(new Field<?>[0]);
		}

		@Override
		protected void saveAction(ClickEvent event) {
			saveInstance();
		}

		@Override
		protected void removeAction(ClickEvent event) {
			removeInstance();
		}

		@Override
		protected void cancelAction(ClickEvent event) {
			discardForm();
		}
	}

	/**
	 * Recupera os campos do formulário, mapeados de acordo com o identificador
	 * da propriedade do campo. No caso, os identificadores equivalem aos nomes
	 * das propriedades no bean. Retorna sempre novas instâncias dos campos.
	 * 
	 * @return Mapa dos campos para as propriedades do bean
	 */
	protected abstract LinkedHashMap<String, Field<?>> getFormFields();
	
	/**
	 * Prepara o formulário para criação de uma nova instância do domínio.
	 */
	private void createInstance() {
		editInstance(getNewInstance());
	}
	
	/**
	 * Recupera uma nova instância do domínio.
	 * 
	 * @return Nova instância do objeto de domínio
	 */
	protected abstract T getNewInstance();
	
	/**
	 * Prepara o formulário para edição de uma instância do domínio.
	 * 
	 * @param instance Instância a ser apresentada no formulário
	 */
	private void editInstance(T instance) {
		formFields.setItemDataSource(instance);
		instanceForm.setVisible(true);
		instanceForm.fields.iterator().next().focus();
		instanceForm.getRemove().setVisible(instance.isPersisted());
		newInstance.setEnabled(instance.isPersisted());
	}
	
	/**
	 * Salva os dados da instância em edição no formulário.
	 */
	private void saveInstance() {
		try {
			formFields.commit();
		} catch (CommitException e) {
			LOGGER.trace(ERROR_VALIDACAO_INSTANCIA, e);
			ApplicationNotification.fail(null, getMessage(VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_VALIDATION));
			return;
		}
		
		T instance = formFields.getItemDataSource().getBean();
		
		try {
			saveInstance(instance);
			ApplicationNotification.success(null, getMessage(VIEW_DOMINIOS_NOTIFICATION_SAVE_SUCCESS, getInstanceDescription(instance)));
			
			discardForm();
			refreshInstances();
		} catch (Exception e) {
			if (e.getCause() instanceof PersistenceException) {
				LOGGER.error(ERROR_PERSISTENCIA_INSTANCIA, e);
				ApplicationNotification.fail(null, getMessage(VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_PERSISTENCE, getInstanceDescription(instance)));
			} else {
				LOGGER.error(ERROR_INESPERADO_PERSISTENCIA_INSTANCIA, e);
				ApplicationNotification.error(null, getMessage(VIEW_DOMINIOS_NOTIFICATION_SAVE_FAIL_UNEXPECTED));
			}
		}
	}
	
	/**
	 * Persiste a atualização dos dados de uma instância na base de dados.
	 * 
	 * @param instance Instância a ser atualizada
	 */
	protected abstract void saveInstance(T instance);

	/**
	 * Apaga a instância em edição no formulário.
	 */
	private void removeInstance() {
		T instance = formFields.getItemDataSource().getBean();
		
		try {
			removeInstance(instance);
			ApplicationNotification.success(null, getMessage(VIEW_DOMINIOS_NOTIFICATION_REMOVE_SUCCESS, getInstanceDescription(instance)));
			
			discardForm();
			refreshInstances();
		} catch (Exception e) {
			if (e.getCause() instanceof PersistenceException) {
				LOGGER.error(ERROR_REMOCAOO_INSTANCIA, e);
				ApplicationNotification.fail(null, getMessage(VIEW_DOMINIOS_NOTIFICATION_REMOVE_FAIL_PERSISTENCE, getInstanceDescription(instance)));
			} else {
				LOGGER.error(ERROR_INESPERADO_REMOCAO_INSTANCIA, e);
				ApplicationNotification.error(null, getMessage(VIEW_DOMINIOS_NOTIFICATION_REMOVE_FAIL_UNEXPECTED));
			}
		}
	}
	
	/**
	 * Remove uma instância da base de dados.
	 * 
	 * @param instance Instância a ser removida
	 */
	protected abstract void removeInstance(T instance);

	/**
	 * Descarta o formulário de edição da instância que está em apresentação.
	 */
	private void discardForm() {
		formFields.setItemDataSource((T) null);
		instanceForm.setVisible(false);
		newInstance.setEnabled(true);
		instanceList.deselect(instanceList.getSelectedRow());
	}
	
	/**
	 * Recupera a descrição e formato de String de ima instância.
	 * 
	 * @param instance Instância cuja descrição deve ser recuperada.
	 * @return Descrição da instância
	 */
	protected abstract String getInstanceDescription(T instance);
}
