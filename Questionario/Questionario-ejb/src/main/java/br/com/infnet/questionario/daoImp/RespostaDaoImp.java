package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.RespostaDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Resposta;
@Stateless
@Local(RespostaDAO.class)
public class RespostaDaoImp extends GenericCRUD<Resposta, Integer> implements RespostaDAO{

}
