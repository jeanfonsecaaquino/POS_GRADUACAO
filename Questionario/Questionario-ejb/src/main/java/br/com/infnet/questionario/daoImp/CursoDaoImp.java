package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.infnet.questionario.dao.CursoDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Curso;
@Stateless
@Local(CursoDAO.class)
public class CursoDaoImp extends GenericCRUD<Curso, Integer> implements CursoDAO{

}
