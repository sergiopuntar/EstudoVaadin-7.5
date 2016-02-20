package br.com.sgpf.common.application.component;

import br.com.sgpf.common.infra.resources.Constants;

import com.vaadin.data.Property;
import com.vaadin.ui.TextArea;

/**
 * Text area customizado com as configurações padrão do sistema
 */
@SuppressWarnings("rawtypes")
public class CustomTextArea extends TextArea {
	private static final long serialVersionUID = -5219225918613149573L;

	public CustomTextArea() {
		super();
		config(null, null, null, null);
	}

	public CustomTextArea(Property dataSource) {
		super(dataSource);
		config(null, null, null, null);
	}

	public CustomTextArea(String caption, Property dataSource) {
		super(caption, dataSource);
		config(null, null, null, null);
	}

	public CustomTextArea(String caption, String value) {
		super(caption, value);
		config(null, null, null, null);
	}

	public CustomTextArea(String caption) {
		super(caption);
		config(null, null, null, null);
	}
	
	public CustomTextArea(String caption, float width, Unit widthUnit) {
		super(caption);
		config(width, widthUnit, null, null);
	}
	
	public CustomTextArea(String caption, float width, Unit widthUnit, Float height, Unit heightUnit) {
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
