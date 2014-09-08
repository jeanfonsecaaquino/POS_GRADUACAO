package br.com.infnet.cdi;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.dao.TurmaDAO;
import br.com.infnet.questionario.dao.UsuarioDAO;
import br.com.infnet.questionario.dto.Curso;
import br.com.infnet.questionario.dto.TURNO;
import br.com.infnet.questionario.dto.Usuario;


@Named
@RequestScoped
public class TurmaService {

	@Inject
	private CursoDAO cursoDAO;
	@EJB
	private UsuarioDAO usuarioDAO;
	@EJB
	private TurmaDAO turmaDAO;
	private DualListModel<Usuario> usuariosModel;
	private List<Usuario> usuariosTodos;
	private List<Usuario> usuariosNaTurma;
	private List<Curso> cursos;
	private List<SelectItem> turnos;
	private Curso cursoSelecionado;
	private TURNO turnoSelecionado;
	private static Logger log = LoggerFactory.getLogger(TurmaService.class);
	
	@Inject
	public TurmaService(){
		try {
			System.out.println("Teste");
			this.cursos = cursoDAO.listar();
			this.usuariosTodos = usuarioDAO.listarAlunos();					
			this.usuariosNaTurma = new ArrayList<Usuario>();
			setUsuariosModel(new DualListModel<Usuario>(usuariosTodos, usuariosNaTurma));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		turnos = new ArrayList<SelectItem>();
		turnos.clear();
		for(TURNO turno : TURNO.values()){
			SelectItem selectItem = new SelectItem(turno, turno.getLabelValue());
			turnos.add(selectItem);
		}
	}

	public void cadastrarTurma(){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			
			facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Turma Cadastrada com Sucesso"));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			facesContext.addMessage("formCadastroTurma", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			e.printStackTrace();
		}
		
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

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public TURNO getTurnoSelecionado() {
		return turnoSelecionado;
	}

	public void setTurnoSelecionado(TURNO turnoSelecionado) {
		this.turnoSelecionado = turnoSelecionado;
	}

	public List<SelectItem> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<SelectItem> turnos) {
		this.turnos = turnos;
	}

	public DualListModel<Usuario> getUsuariosModel() {
		return usuariosModel;
	}

	public void setUsuariosModel(DualListModel<Usuario> usuariosModel) {
		this.usuariosModel = usuariosModel;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
}
