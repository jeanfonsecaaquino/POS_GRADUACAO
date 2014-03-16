package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.UsuarioDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Usuario;
@Stateless
@Local(UsuarioDAO.class)

public class UsuarioDaoImp extends GenericCRUD<Usuario, Integer> implements UsuarioDAO{

}
