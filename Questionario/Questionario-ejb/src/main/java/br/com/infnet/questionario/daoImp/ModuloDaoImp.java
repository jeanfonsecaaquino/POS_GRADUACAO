package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.ModuloDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Modulo;

@Stateless
@Local(ModuloDAO.class)
public class ModuloDaoImp extends GenericCRUD<Modulo, Integer> implements ModuloDAO{

}
