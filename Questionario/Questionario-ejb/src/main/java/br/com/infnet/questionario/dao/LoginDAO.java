package br.com.infnet.questionario.dao;

import br.com.infnet.questionario.daofactoryimp.GenericDAO;
import br.com.infnet.questionario.dto.Login;

public interface LoginDAO extends GenericDAO<Login, Integer>{

	public Login efetuarLogin(Login login);
	
}
