package br.com.infnet.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.autenticacao.ProvedorAutenticacao;
import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.dto.Curso;

@Named("MBListarCursos")
@ViewScoped
public class ListarCursos {

	private List<Curso> cursos;
	private static Logger log = LoggerFactory.getLogger(ProvedorAutenticacao.class);
	
	@EJB
	private CursoDAO cursoDao;
	
	@PostConstruct
	public void init(){
		if(cursos==null){
			try {
				cursos = cursoDao.listar();
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
}
