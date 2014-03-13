package br.com.infnet.questionario.daoImp;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Session;

import br.com.infnet.questionario.dao.UsuarioDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Login;
import br.com.infnet.questionario.dto.PERFIL;
import br.com.infnet.questionario.dto.Usuario;
@Stateless
@Local(UsuarioDAO.class)

public class UsuarioDaoImp extends GenericCRUD<Usuario, Integer> implements UsuarioDAO{

	Usuario usuario;
	Login login;
	
	public Usuario salvar(Usuario usuario, Login login) throws Exception {
		
		Session session = (Session) getEntityManager().getDelegate();
		session.beginTransaction();
		login.setPerfil(PERFIL.A);
		session.save(login);
		usuario.setLogin(login);
		session.save(usuario);
		session.getTransaction().commit();
		return usuario;
		
	}	
}
