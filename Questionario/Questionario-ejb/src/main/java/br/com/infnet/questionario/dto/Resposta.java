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
@Table(name="resposta")
public class Resposta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cod_resposta")
	private Integer codResposta;
	
	@ManyToOne
	@JoinColumn(name="cod_questao")
	private Questao questao;
	
	@ManyToOne
	@JoinColumn(name="cod_avaliacao")
	private Avaliacao avaliacao;
	
	public Resposta() {
	}

	public Resposta(Integer codResposta, Questao questao, Avaliacao avaliacao) {
		this.codResposta = codResposta;
		this.questao = questao;
		this.avaliacao = avaliacao;
	}

	public Integer getCodResposta() {
		return codResposta;
	}

	public void setCodResposta(Integer codResposta) {
		this.codResposta = codResposta;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
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
				+ ((codResposta == null) ? 0 : codResposta.hashCode());
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
		Resposta other = (Resposta) obj;
		if (codResposta == null) {
			if (other.codResposta != null)
				return false;
		} else if (!codResposta.equals(other.codResposta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resposta [codResposta=" + codResposta + "]";
	}
	
}
