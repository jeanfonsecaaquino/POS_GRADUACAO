package br.com.infnet.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.dto.Curso;

@ManagedBean(name = "MBCadastrarCurso")
@ViewScoped
public class CadastrarCurso implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private CursoDAO cursoDao;
	
	@Inject
	private Curso curso;
	
	
	private static Logger log = LoggerFactory.getLogger(CadastrarQuestao.class);
	
	public void cadastrarCurso(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			facesContext.addMessage("formCadastroCurso", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Curso Cadastrado com Sucesso"));
			cursoDao.salvar(curso);
		} catch (Exception e) {
			facesContext.addMessage("formCadastroQuestao", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			log.error(e.getMessage(),e);
		}
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
