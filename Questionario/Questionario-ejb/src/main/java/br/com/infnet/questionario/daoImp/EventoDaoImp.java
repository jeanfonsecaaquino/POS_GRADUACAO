package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.EventoDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Evento;

@Stateless
@Local(EventoDAO.class)
public class EventoDaoImp extends GenericCRUD<Evento, Integer> implements EventoDAO{

}
