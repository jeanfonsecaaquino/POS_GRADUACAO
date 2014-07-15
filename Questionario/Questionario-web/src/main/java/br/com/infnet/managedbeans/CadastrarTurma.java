package br.com.infnet.managedbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.TurmaDAO;
import br.com.infnet.questionario.dto.Turma;

@ManagedBean(name="MBCadastrarTurma")
@RequestScoped
public class CadastrarTurma {

	@Inject
	private Turma turma;
	
	@EJB
	private TurmaDAO turmaDAO;
	
	private static Logger log = LoggerFactory.getLogger(CadastrarTurma.class);
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void cadastrarTurma(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Turma Cadastrada com Sucesso"));
			turmaDAO.salvar(turma);
		} catch (Exception e) {
			facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			log.error(e.getMessage(),e);
		}
	}
	
}
