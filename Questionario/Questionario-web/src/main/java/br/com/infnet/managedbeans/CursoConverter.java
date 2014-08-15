package br.com.infnet.managedbeans;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.infnet.questionario.dto.Curso;

@FacesConverter(forClass=Curso.class, value = "FCCursoConverter")
@RequestScoped
public class CursoConverter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorSubmetido) {
		Curso curso = new Curso();
		curso.setCodCurso(Integer.parseInt(valorSubmetido));
		return curso;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valorSubmetido) {
		return String.valueOf(((Curso) valorSubmetido).getCodCurso());
	}
}
