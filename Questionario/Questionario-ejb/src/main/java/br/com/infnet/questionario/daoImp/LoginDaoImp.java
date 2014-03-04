package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.infnet.questionario.dao.LoginDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Login;

@Stateless
@Local(LoginDAO.class)
public class LoginDaoImp extends GenericCRUD<Login, Integer> implements LoginDAO{

	private Criteria criteria;
	
	public Login efetuarLogin(Login login){
		Session session = (Session) getEntityManager().getDelegate();
		criteria = session.createCriteria(Login.class);
		criteria.add(Restrictions.and(Restrictions.eq("login", login.getLogin()), Restrictions.eq("senha", login.getSenha())));
		return (Login) criteria.uniqueResult();
	}
	
}
