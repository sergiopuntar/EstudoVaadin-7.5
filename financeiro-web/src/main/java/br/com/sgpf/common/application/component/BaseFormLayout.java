package br.com.sgpf.common.application.component;

import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.BUTTON_CANCEL_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.BUTTON_REMOVE_LABEL;
import static br.com.sgpf.app.financeiro.infra.i18n.FinanceiroMessageKey.BUTTON_SAVE_LABEL;
import static br.com.sgpf.common.infra.i18n.ApplicationMessages.getMessage;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Classe abstrata base para formulários simples.
 */
public abstract class BaseFormLayout extends FormLayout {
	private static final long serialVersionUID = -3865234654352055170L;

	private Button save;
	private Button remove;
	private Button cancel;
	
	public BaseFormLayout() {
		super();
		setMargin(true);
	}

	/**
	 * Constrói o conteúdo padrão do formulário.
	 */
	protected void buildContent() {
		addComponents(getFields());
		addComponent(buildFormActionButtons());
	}

	/**
	 * Recupera os campos do formulário.
	 * 
	 * @return Campos do formulário
	 */
	protected abstract Field<?>[] getFields();

	/**
	 * Constrói os botões de ação do formulário.
	 * 
	 * @return Container dos botões de ação
	 */
	private HorizontalLayout buildFormActionButtons() {
		save = new Button(getMessage(BUTTON_SAVE_LABEL));
		save.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		save.setIcon(FontAwesome.SAVE);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		save.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 6771622959378154670L;

			@Override
			public void buttonClick(ClickEvent event) {
				saveAction(event);
			}
		});
		
		remove = new Button(getMessage(BUTTON_REMOVE_LABEL));
		remove.setStyleName(ValoTheme.BUTTON_DANGER);
		remove.setIcon(FontAwesome.TRASH_O);
		remove.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 4465680128784124543L;

			@Override
			public void buttonClick(ClickEvent event) {
				removeAction(event);
			}
		});
		
		cancel = new Button(getMessage(BUTTON_CANCEL_LABEL));
		cancel.setIcon(FontAwesome.BAN);
		cancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 3477000093071300094L;

			@Override
			public void buttonClick(ClickEvent event) {
				cancelAction(event);
			}
		});
		
		HorizontalLayout horizontalLayout = new HorizontalLayout(save, remove, cancel);
		horizontalLayout.setSpacing(true);
		
		return horizontalLayout;
	}
	
	/**
	 * Executa a ação do evento de salvamento.
	 * 
	 * @param event Evento de click que disparou o salvamento
	 */
	protected abstract void saveAction(ClickEvent event);
	
	/**
	 * Executa a ação do evento de remoção.
	 * 
	 * @param event Evento de click que disparou a remoção
	 */
	protected abstract void removeAction(ClickEvent event);
	
	/**
	 * Executa a ação do evento de cancelamento.
	 * 
	 * @param event Evento de click que disparou o cancelamento
	 */
	protected abstract void cancelAction(ClickEvent event);

	public Button getSave() {
		return save;
	}

	public Button getRemove() {
		return remove;
	}

	public Button getCancel() {
		return cancel;
	}
}
