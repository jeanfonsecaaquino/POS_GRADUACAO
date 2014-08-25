package br.com.infnet.questionario.dao;

import java.util.List;

import br.com.infnet.questionario.daofactoryimp.GenericDAO;
import br.com.infnet.questionario.dto.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer>{

	List<Usuario> listarAlunos();

}
