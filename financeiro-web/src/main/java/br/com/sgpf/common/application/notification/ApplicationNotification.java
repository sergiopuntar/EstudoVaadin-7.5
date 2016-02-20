package br.com.sgpf.common.application.notification;

import static com.vaadin.shared.Position.TOP_CENTER;
import static com.vaadin.shared.Position.BOTTOM_CENTER;
import static com.vaadin.shared.Position.BOTTOM_RIGHT;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_CLOSABLE;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_ERROR;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_FAILURE;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_SMALL;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_SUCCESS;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_TRAY;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_WARNING;
import static com.vaadin.ui.themes.ValoTheme.NOTIFICATION_BAR;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

/**
 * Classe responsável por realizar notificações no sistema.
 */
public class ApplicationNotification {
	private static final String INFO_STYLE = NOTIFICATION_TRAY + " " + NOTIFICATION_SMALL + " " + NOTIFICATION_CLOSABLE;
	private static final String ALERT_STYLE = "";
	private static final String WARN_STYLE = NOTIFICATION_WARNING;
	private static final String SUCCESS_STYLE = NOTIFICATION_SUCCESS;
	private static final String FAIL_STYLE = NOTIFICATION_FAILURE;
	private static final String FATAL_STYLE = NOTIFICATION_ERROR + " " + NOTIFICATION_CLOSABLE + " " + NOTIFICATION_BAR;

	/**
	 * Apresenta uma notificação informativa de pouco relevância.
	 * 
	 * @param caption Título da notificação
	 * @param description Descrição da notificação
	 */
	public static void info(String caption, String description) {
		showNotification(caption, description, INFO_STYLE, BOTTOM_RIGHT, 3000);
	}
	
	/**
	 * Apresenta uma notificação informativa de média relevância.
	 * 
	 * @param caption Título da notificação
	 * @param description Descrição da notificação
	 */
	public static void alert(String caption, String description) {
		showNotification(caption, description, ALERT_STYLE, BOTTOM_CENTER, 2000);
	}
	
	/**
	 * Apresenta uma notificação informativa de alta relevância.
	 * 
	 * @param caption Título da notificação
	 * @param description Descrição da notificação
	 */
	public static void warn(String caption, String description) {
		showNotification(caption, description, WARN_STYLE, BOTTOM_CENTER, 2000);
	}
	
	/**
	 * Apresenta uma notificação informativa de sucesso.
	 * 
	 * @param caption Título da notificação
	 * @param description Descrição da notificação
	 */
	public static void success(String caption, String description) {
		showNotification(caption, description, SUCCESS_STYLE, TOP_CENTER, 1500);
	}

	/**
	 * Apresenta uma notificação informativa de falha.
	 * 
	 * @param caption Título da notificação
	 * @param description Descrição da notificação
	 */
	public static void fail(String caption, String description) {
		showNotification(caption, description, FAIL_STYLE, TOP_CENTER, 1500);
	}

	/**
	 * Apresenta uma notificação informativa de erro.
	 * 
	 * @param caption Título da notificação
	 * @param description Descrição da notificação
	 */
	public static void error(String caption, String description) {
		showNotification(caption, description, FATAL_STYLE, BOTTOM_CENTER, -1);
	}
	
	/**
	 * Apreenta uma notificação na página atual.
	 * 
	 * @param caption Título da notificação
	 * @param description Descrição da notificação
	 * @param styleName Estuilo da notificação
	 * @param position Posição de apresentação da notificação
	 * @param delay Tempo de espera para desaparecimento da notificação
	 */
	private static void showNotification(String caption, String description, String styleName, Position position, int delay) {
		Notification notification = new Notification(caption, description);
		notification.setStyleName(styleName);
		notification.setPosition(position);
		notification.setDelayMsec(delay);
		notification.show(Page.getCurrent());
	}
}
