package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.dao.TurmaDAO;
import br.com.infnet.questionario.dao.UsuarioDAO;
import br.com.infnet.questionario.dto.Curso;
import br.com.infnet.questionario.dto.TURNO;
import br.com.infnet.questionario.dto.Turma;
import br.com.infnet.questionario.dto.Usuario;

@ManagedBean(name="MBCadastrarTurma")
@ViewScoped
public class CadastrarTurma implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Turma turma;
	@Inject
	private TurmaDAO turmaDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private CursoDAO cursoDAO;
	private Curso curso;
	private List<Curso> cursos;

	private List<TURNO> turnos;

	private DualListModel<Usuario> usuariosModel;

	@PostConstruct
	public void init(){
	
		if(turnos == null){
			turnos = new ArrayList<TURNO>();
			for(TURNO turno : TURNO.values()){
				turnos.add(turno);
			}
		}
	}


private static Logger log = LoggerFactory.getLogger(CadastrarTurma.class);

public Turma getTurma() {
	return turma;
}

public void setTurma(Turma turma) {
	this.turma = turma;
}

public List<TURNO> getTurnos() {
	return turnos;
}

public void setTurnos(List<TURNO> turnos) {
	this.turnos = turnos;
}

public Curso getCurso() {
	return curso;
}

public void setCurso(Curso curso) {
	this.curso = curso;
}

public DualListModel<Usuario> getUsuariosModel() {
	if(usuariosModel==null){
		try{
			List<Usuario> usuariosTodos = usuarioDAO.listarAlunos();					
			List<Usuario> usuariosNaTurma = new ArrayList<Usuario>();
			usuariosModel = new DualListModel<Usuario>(usuariosTodos, usuariosNaTurma);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	return usuariosModel;
}

public void setUsuariosModel(DualListModel<Usuario> usuariosModel) {
	this.usuariosModel = usuariosModel;
}

public List<Curso> getCursos() {
	if(cursos == null){

		try {
			cursos = cursoDAO.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return cursos;
}

public void setCursos(List<Curso> cursos) {
	this.cursos = cursos;
}

public void cadastrarTurma(){
	FacesContext facesContext = FacesContext.getCurrentInstance();
	try {
		turma.setCurso(curso);
		turma.setUsuarios(usuariosModel.getTarget());
		TURNO turno = (TURNO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(turnos);
		turma.setTurno(turno);
		facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Turma Cadastrada com Sucesso"));
		turmaDAO.salvar(turma);
	} catch (Exception e) {
		facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
		log.error(e.getMessage(),e);
	}
}

}
