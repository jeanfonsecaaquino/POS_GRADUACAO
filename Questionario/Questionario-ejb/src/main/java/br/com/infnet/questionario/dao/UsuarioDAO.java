package br.com.infnet.questionario.dao;

import br.com.infnet.questionario.daofactoryimp.GenericDAO;
import br.com.infnet.questionario.dto.Login;
import br.com.infnet.questionario.dto.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer>{

	public Usuario salvar(Usuario usuario,Login login) throws Exception;
	
}
