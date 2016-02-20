package br.com.sgpf.app.financeiro.application.view.contas;

import java.util.Calendar;

import javax.enterprise.context.Dependent;

import br.com.sgpf.app.financeiro.domain.valueobject.Mes;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Dependent
public class ExtratoConta extends HorizontalLayout {
	private static final long serialVersionUID = -6854446419459804553L;
	
	private SaldoContaCard saldoContaCard;
	
	private TabSheet meses;
	
	public ExtratoConta() {
		super();
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
		setMargin(new MarginInfo(false, true, true, true));
	}

	public void buildContent(SaldoContaCard saldoContaCard) {
		this.saldoContaCard = saldoContaCard;
		addComponent(buildMeses());
	}

	private TabSheet buildMeses() {
		meses = new TabSheet();
		meses.setWidth(70, Unit.PERCENTAGE);
		meses.setHeight(100, Unit.PERCENTAGE);
		meses.addStyleName(ValoTheme.TABSHEET_FRAMED);
		meses.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		meses.addStyleName(ValoTheme.TABSHEET_COMPACT_TABBAR);
        
		for (int i = 0; i <= Calendar.getInstance().get(Calendar.MONTH); i++) {
			VerticalLayout layout = new VerticalLayout();
			layout.setHeight(100, Unit.PERCENTAGE);
			layout.setCaption(saldoContaCard.getSaldoConta().getConta().getBanco().getNome());
			meses.addTab(layout, Mes.values()[i].getNome());
		}
		
		return meses;
	}
}
