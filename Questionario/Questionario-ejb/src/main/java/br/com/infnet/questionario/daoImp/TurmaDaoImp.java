package br.com.infnet.questionario.daoImp;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Session;

import br.com.infnet.questionario.dao.TurmaDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Curso;
import br.com.infnet.questionario.dto.Turma;
import br.com.infnet.questionario.dto.Usuario;

@Stateless
@Local(TurmaDAO.class)
public class TurmaDaoImp extends GenericCRUD<Turma, Integer> implements TurmaDAO{
	
	public Turma salvar(Curso curso, List<Usuario> usuarios ) throws Exception{
		Session session = (Session) getEntityManager().getDelegate();
		session.beginTransaction();
		
		Turma turma = new Turma();
		turma.setCurso(curso);
		turma.setUsuarios(usuarios);
		turma.setCodTurma((Integer) session.save(turma));
		session.getTransaction().commit();
		return turma;
		
	}
}
