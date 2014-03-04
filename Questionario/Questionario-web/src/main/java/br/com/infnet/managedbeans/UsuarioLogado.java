package br.com.infnet.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.infnet.autenticacao.Autenticacao;
import br.com.infnet.questionario.dto.Login;

@Named("MBUsuarioLogado")
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
