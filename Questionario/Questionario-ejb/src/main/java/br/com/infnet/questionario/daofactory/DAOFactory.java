package br.com.infnet.questionario.daofactory;

import javax.ejb.Local;

import br.com.infnet.questionario.daofactoryimp.GenericCRUD;

@Local
public interface DAOFactory {

	GenericCRUD<?, ?> getDAO(Class<?> entidade) throws Exception;
}
