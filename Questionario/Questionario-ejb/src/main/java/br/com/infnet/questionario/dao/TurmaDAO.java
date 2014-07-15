package br.com.infnet.questionario.dao;

import java.util.List;

import br.com.infnet.questionario.daofactoryimp.GenericDAO;
import br.com.infnet.questionario.dto.Curso;
import br.com.infnet.questionario.dto.Turma;
import br.com.infnet.questionario.dto.Usuario;

public interface TurmaDAO extends GenericDAO<Turma, Integer>{
	
	public Turma salvar(Curso curso, List<Usuario> usuarios) throws Exception;
	
}
