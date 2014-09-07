package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.dto.Curso;

@ManagedBean(name="MBListarCursos")
@ViewScoped
public class ListarCursos implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<SelectItem> cursos;
	private static Logger log = LoggerFactory.getLogger(ListarCursos.class);
	
	@EJB
	private CursoDAO cursoDao;
	
	@PostConstruct
	public void init(){
		try {
			cursos = new ArrayList<SelectItem>();
			cursos.clear();
			for (Curso curso : cursoDao.listar()) {
				SelectItem selectItem = new SelectItem(curso.getCodCurso(),curso.getNome());
				cursos.add(selectItem);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public List<SelectItem> getCursos() {
		return cursos;
	}

	public void setCursos(List<SelectItem> cursos) {
		this.cursos = cursos;
	}

}
