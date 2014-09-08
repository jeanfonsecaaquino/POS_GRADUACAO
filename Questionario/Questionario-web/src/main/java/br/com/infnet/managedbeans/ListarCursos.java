package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.dto.Curso;

@ManagedBean(name="MBListarCursos")
@ViewScoped
public class ListarCursos implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Curso> cursos;
	private static Logger log = LoggerFactory.getLogger(ListarCursos.class);
	
	@Inject
	private CursoDAO cursoDao;
	
	@PostConstruct
	public void init(){
	
			try {
				cursos = cursoDao.listar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
