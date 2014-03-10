package br.com.infnet.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.infnet.autenticacao.Autenticacao;
import br.com.infnet.questionario.dto.Login;

@ManagedBean(name="MBUsuarioLogado")
@SessionScoped
public class UsuarioLogado {

	private Login login;
	
	@PostConstruct
	public void setUserLoged(){
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Autenticacao autenticacao = (Autenticacao) context.getAuthentication();
			if (autenticacao instanceof Authentication) {
				login = autenticacao.getLogin();
			}
		}	
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	
}
