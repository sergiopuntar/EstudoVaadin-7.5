package br.com.sgpf.common.application.component;

import br.com.sgpf.common.infra.resources.Constants;

import com.vaadin.data.Property;
import com.vaadin.ui.TextField;

/**
 * Text field customizado com as configurações padrão do sistema
 */
@SuppressWarnings("rawtypes")
public class CustomTextField extends TextField {
	private static final long serialVersionUID = 4529058566267454506L;

	public CustomTextField() {
		super();
		config(null, null, null, null);
	}

	public CustomTextField(Property dataSource) {
		super(dataSource);
		config(null, null, null, null);
	}

	public CustomTextField(String caption, Property dataSource) {
		super(caption, dataSource);
		config(null, null, null, null);
	}

	public CustomTextField(String caption, String value) {
		super(caption, value);
		config(null, null, null, null);
	}

	public CustomTextField(String caption) {
		super(caption);
		config(null, null, null, null);
	}
	
	public CustomTextField(String caption, float width, Unit widthUnit) {
		super(caption);
		config(width, widthUnit, null, null);
	}
	
	public CustomTextField(String caption, float width, Unit widthUnit, Float height, Unit heightUnit) {
		super(caption);
		config(width, widthUnit, height, heightUnit);
	}

	/**
	 * Configura o componente.
	 */
	private void config(Float width, Unit widthUnit, Float height, Unit heightUnit) {
		setNullRepresentation(Constants.STR_EMPTY);
		
		if (width != null) {
			setWidth(width, widthUnit);			
		}
		
		if (height != null) {
			setHeight(height, heightUnit);
		}
	}
}
