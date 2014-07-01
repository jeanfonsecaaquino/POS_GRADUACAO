package br.com.infnet.questionario.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="avaliacao")
public class Avaliacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_avaliacao")
	private Integer codAvaliacao;
	
	@OneToMany(mappedBy="avaliacao",cascade=CascadeType.ALL)
	private List<Resposta> respostas;
	
	@OneToMany(mappedBy="avaliacao",cascade=CascadeType.ALL)
	private List<Comentario> comentarios;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="avaliacao")
	private List<Evento> eventos;
	
	public Avaliacao() {
	}
	
	public Avaliacao(Integer codAvaliacao) {
		super();
		this.codAvaliacao = codAvaliacao;
	}

	public Integer getCodAvaliacao() {
		return codAvaliacao;
	}

	public void setCodAvaliacao(Integer codAvaliacao) {
		this.codAvaliacao = codAvaliacao;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codAvaliacao == null) ? 0 : codAvaliacao.hashCode());
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
		Avaliacao other = (Avaliacao) obj;
		if (codAvaliacao == null) {
			if (other.codAvaliacao != null)
				return false;
		} else if (!codAvaliacao.equals(other.codAvaliacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Avaliacao [codAvaliacao=" + codAvaliacao + "]";
	}
	
}
