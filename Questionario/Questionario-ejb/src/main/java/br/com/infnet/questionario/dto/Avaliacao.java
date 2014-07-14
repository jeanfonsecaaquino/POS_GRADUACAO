package br.com.infnet.questionario.dto;

import java.io.Serializable;
import java.util.Date;
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
	
	@Column
	private Date dataInicio;
	
	@Column
	private Date dataFim;
	
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

	/**
	 * @return the eventos
	 */
	public List<Evento> getEventos() {
		return eventos;
	}

	/**
	 * @param eventos the eventos to set
	 */
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codAvaliacao == null) ? 0 : codAvaliacao.hashCode());
		result = prime * result
				+ ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((eventos == null) ? 0 : eventos.hashCode());
		result = prime * result
				+ ((respostas == null) ? 0 : respostas.hashCode());
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
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (eventos == null) {
			if (other.eventos != null)
				return false;
		} else if (!eventos.equals(other.eventos))
			return false;
		if (respostas == null) {
			if (other.respostas != null)
				return false;
		} else if (!respostas.equals(other.respostas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Avaliacao [codAvaliacao=" + codAvaliacao + ", respostas="
				+ respostas + ", comentarios=" + comentarios + ", eventos="
				+ eventos + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + "]";
	}
	
}
