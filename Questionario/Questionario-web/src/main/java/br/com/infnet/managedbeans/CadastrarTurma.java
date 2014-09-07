package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	@EJB
	private TurmaDAO turmaDAO;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	private List<SelectItem> turnos;
	
	@Inject
	private Curso curso;
	
	private DualListModel<Usuario> usuariosModel;
	private List<Usuario> usuariosTodos;
	private List<Usuario> usuariosNaTurma;
	
	@PostConstruct
	public void init(){
			usuariosTodos = usuarioDAO.listarAlunos();					
			usuariosNaTurma = new ArrayList<Usuario>();
			setUsuariosModel(new DualListModel<Usuario>(usuariosTodos, usuariosNaTurma));
		turnos = new ArrayList<SelectItem>();
		turnos.clear();
		for(TURNO turno : TURNO.values()){
			SelectItem selectItem = new SelectItem(turno, turno.getLabelValue());
			turnos.add(selectItem);
		}
	}
	
	private static Logger log = LoggerFactory.getLogger(CadastrarTurma.class);
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<SelectItem> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<SelectItem> turnos) {
		this.turnos = turnos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public DualListModel<Usuario> getUsuariosModel() {
		return usuariosModel;
	}

	public void setUsuariosModel(DualListModel<Usuario> usuariosModel) {
		this.usuariosModel = usuariosModel;
	}

	public List<Usuario> getUsuariosTodos() {
		return usuariosTodos;
	}

	public void setUsuariosTodos(List<Usuario> usuariosTodos) {
		this.usuariosTodos = usuariosTodos;
	}

	public List<Usuario> getUsuariosNaTurma() {
		return usuariosNaTurma;
	}

	public void setUsuariosNaTurma(List<Usuario> usuariosNaTurma) {
		this.usuariosNaTurma = usuariosNaTurma;
	}

	public void cadastrarTurma(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			turma.setCurso(curso);
			List<Usuario> usuarios = usuariosModel.getTarget();
			facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Turma Cadastrada com Sucesso"));
			turmaDAO.salvar(turma);
		} catch (Exception e) {
			facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			log.error(e.getMessage(),e);
		}
	}
	
}
