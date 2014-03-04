package br.com.infnet.questionario.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questao")
public class Questao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cod_questao")
	private Integer codQuestao;
	
	@Column
	private String descricao;
	
	@Column
	@Enumerated(EnumType.STRING)
	private OPCAO opcao;
	
	public Questao() {
	}

	public Questao(Integer codQuestao, String descricao, OPCAO opcao) {
		this.codQuestao = codQuestao;
		this.descricao = descricao;
		this.opcao = opcao;
	}

	public Integer getCodQuestao() {
		return codQuestao;
	}

	public void setCodQuestao(Integer codQuestao) {
		this.codQuestao = codQuestao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public OPCAO getOpcao() {
		return opcao;
	}

	public void setOpcao(OPCAO opcao) {
		this.opcao = opcao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codQuestao == null) ? 0 : codQuestao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((opcao == null) ? 0 : opcao.hashCode());
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
		Questao other = (Questao) obj;
		if (codQuestao == null) {
			if (other.codQuestao != null)
				return false;
		} else if (!codQuestao.equals(other.codQuestao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (opcao != other.opcao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Questao [codQuestao=" + codQuestao + ", descricao=" + descricao
				+ ", opcao=" + opcao + "]";
	}
	
}
