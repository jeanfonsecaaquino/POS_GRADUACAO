package br.com.infnet.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.infnet.managedbeans.CadastrarModulo;
import br.com.infnet.questionario.dao.CursoDAO;

@FacesConverter(forClass=CadastrarModulo.class)
public class ModuloConverter implements Converter, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
	public CursoDAO cursoDAO;
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor!=null && valor.trim().length()>0){
			Integer id = Integer.valueOf(valor);
			try {
				return cursoDAO.buscarPorId(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object curso) {
		// TODO Auto-generated method stub
		return null;
	}

}
