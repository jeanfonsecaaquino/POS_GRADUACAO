package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.QuestaoDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Questao;
@Stateless
@Local(QuestaoDAO.class)
public class QuestaoDaoImp extends GenericCRUD<Questao, Integer> implements QuestaoDAO{

}
