package br.com.infnet.questionario.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="modulo")
public class Modulo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_modulo")
	private Integer codModulo;
	
	@Column
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="cod_curso")
	private Curso curso;
	
	public Modulo() {
	}

	public Modulo(Integer codModulo, String nome) {
		this.codModulo = codModulo;
		this.nome = nome;
	}

	public Integer getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(Integer codModulo) {
		this.codModulo = codModulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codModulo == null) ? 0 : codModulo.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modulo other = (Modulo) obj;
		if (codModulo == null) {
			if (other.codModulo != null)
				return false;
		} else if (!codModulo.equals(other.codModulo))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Modulo [codModulo=" + codModulo + ", nome=" + nome + "]";
	}
	
}
