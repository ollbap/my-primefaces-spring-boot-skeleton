package es.test.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = String.class)
public class StringConverter implements Converter<String> {

	@Override
	@SuppressWarnings("null")
	public String getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.contains("#")) {
			FacesMessage msg = new FacesMessage("String can not contain #");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
		return value;
	}

	@Override
	@SuppressWarnings("null")
	public String getAsString(FacesContext context, UIComponent component, String value) {
		return value;
	}

}