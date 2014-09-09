package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.dao.ModuloDAO;
import br.com.infnet.questionario.dto.Curso;
import br.com.infnet.questionario.dto.Modulo;

@ManagedBean(name="MBCadastrarModulo")
@ViewScoped
public class CadastrarModulo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ModuloDAO moduloDAO;
	@Inject
	private CursoDAO cursoDAO;
	private Curso curso = new Curso();
	private List<Curso> cursos;
	private Modulo modulo = new Modulo();
	private List<Modulo> modulos;
	private String nome;
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public List<Curso> getCursos() {
		try {
			cursos = cursoDAO.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cursos;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public List<Modulo> getModulos() {
		try {
			modulos = moduloDAO.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modulos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void cadastrarModulo(){
		try {
			this.modulo.setCodModulo(curso.getCodCurso());
			this.modulo.setCurso(curso);
			this.modulo.setNome(nome);
			moduloDAO.salvar(this.modulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
