package br.com.infnet.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.infnet.questionario.dto.Usuario;

@FacesConverter(value = "MBUsuarioConverter")
public class UsuarioConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorSubmetido) {
		Usuario usuario = null;
		return usuario;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object modelValue) {
		if(modelValue == null){
			return null;
		}
		if(modelValue instanceof Usuario){
			return String.valueOf(((Usuario) modelValue).getCodUsuario());
		}else{
			throw new ConverterException(new FacesMessage(String.format("Valor de ID do Usuario inválido", modelValue)));
		}
	}
	
	

}

	