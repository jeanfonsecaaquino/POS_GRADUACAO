package br.com.infnet.autenticacao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import br.com.infnet.questionario.dto.Login;


public class Autenticacao implements Authentication{
	
	private static final long serialVersionUID = 1L;
	private boolean authenticated;
	private GrantedAuthority grantedAuthority;
	private Authentication authentication;
	private Login login;

	public Autenticacao(String role, Authentication authentication) {
		this.grantedAuthority = new GrantedAuthorityImpl(role);
		this.authentication = authentication;
	}
	
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(grantedAuthority);
		return authorities;
	}
	
	public Object getCredentials() {
		return authentication.getCredentials();
	}
	
	public Object getDetails() {
		return authentication.getDetails();
	}

	public Object getPrincipal() {
		return authentication.getPrincipal();
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}


	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public GrantedAuthority getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(GrantedAuthority grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}
	
}
