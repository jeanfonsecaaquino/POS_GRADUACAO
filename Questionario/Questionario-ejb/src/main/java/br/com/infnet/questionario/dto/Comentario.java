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
@Table(name="comentario")
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cod_comentario")
	private Integer codComentario;
	
	@Column(columnDefinition="TEXT")
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name="cod_avaliacao")
	private Avaliacao avaliacao;
	
	@ManyToOne
	@JoinColumn(name="cod_usuario")
	private Usuario usuario;
	
	public Comentario() {
	}

	public Comentario(Integer codComentario, String comentario) {
		this.codComentario = codComentario;
		this.comentario = comentario;
	}

	public Integer getCodComentario() {
		return codComentario;
	}

	public void setCodComentario(Integer codComentario) {
		this.codComentario = codComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codComentario == null) ? 0 : codComentario.hashCode());
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
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
		Comentario other = (Comentario) obj;
		if (codComentario == null) {
			if (other.codComentario != null)
				return false;
		} else if (!codComentario.equals(other.codComentario))
			return false;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comentario [codComentario=" + codComentario + ", comentario="
				+ comentario + "]";
	}
	
}
