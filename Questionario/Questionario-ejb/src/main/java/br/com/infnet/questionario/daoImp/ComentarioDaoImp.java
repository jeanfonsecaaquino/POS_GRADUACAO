package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.ComentarioDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Comentario;
@Stateless
@Local(ComentarioDAO.class)
public class ComentarioDaoImp extends GenericCRUD<Comentario, Integer> implements ComentarioDAO{

}
