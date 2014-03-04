package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.AvaliacaoDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Avaliacao;
@Stateless
@Local(AvaliacaoDAO.class)
public class AvaliacaoDaoImp extends GenericCRUD<Avaliacao, Integer> implements AvaliacaoDAO{

}
