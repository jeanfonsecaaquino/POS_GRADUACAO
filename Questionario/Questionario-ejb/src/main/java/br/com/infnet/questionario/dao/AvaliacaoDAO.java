package br.com.infnet.questionario.dao;

import java.util.List;

import br.com.infnet.questionario.daofactoryimp.GenericDAO;
import br.com.infnet.questionario.dto.Avaliacao;
import br.com.infnet.questionario.dto.Comentario;
import br.com.infnet.questionario.dto.Questao;

public interface AvaliacaoDAO extends GenericDAO<Avaliacao, Integer>{

	public Avaliacao salvar(Comentario comentario, List<Questao> questoes) throws Exception;
	
}
