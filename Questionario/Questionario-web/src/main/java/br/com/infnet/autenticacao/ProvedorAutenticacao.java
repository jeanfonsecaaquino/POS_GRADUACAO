package br.com.infnet.autenticacao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.infnet.questionario.dao.LoginDAO;
import br.com.infnet.questionario.dto.Login;
import br.com.infnet.utils.QuestaoUtils;

public class ProvedorAutenticacao implements AuthenticationProvider{
	
	private LoginDAO loginDao;
	private Login login;
	private QuestaoUtils questaoUtils;
	private Autenticacao autenticacao;
	private static Logger log = LoggerFactory.getLogger(ProvedorAutenticacao.class);
	
	public LoginDAO lookupEjb() throws NamingException{
		Context context = new InitialContext();
		return (LoginDAO) context.lookup("java:app/Questionario-ejb/LoginDaoImp");
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			loginDao = lookupEjb();
			login = new Login();
			questaoUtils = new QuestaoUtils();
			validarCampos(authentication);
			login.setLogin(authentication.getCredentials().toString());
			login.setSenha(questaoUtils.encrypt(authentication.getPrincipal().toString()));
			
			login = loginDao.efetuarLogin(login);
			
			if(login!=null){
				autenticacao = new Autenticacao(login.getPerfil().getStringValue(), authentication);
				autenticacao.setAuthenticated(true);
				autenticacao.setLogin(login);
				autenticacao.setGrantedAuthority(new GrantedAuthorityImpl(login.getPerfil().getStringValue()));
			}else{
				throw new UsernameNotFoundException("O Usuário "+ authentication.getCredentials().toString() +" não foi encontrado");
			}
			
			return autenticacao;
			
		} catch (UsernameNotFoundException e) {
			log.warn(e.getMessage(),e);
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		return null;
	}

	public boolean supports(Class<? extends Object> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	public void validarCampos(Authentication authentication){
		if(authentication.getCredentials().toString()==null || authentication.getCredentials().toString().equalsIgnoreCase("")){
			throw new UsernameNotFoundException("Login não Informado!");
		}
		if(authentication.getPrincipal().toString()==null || authentication.getPrincipal().toString().equalsIgnoreCase("")){
			throw new UsernameNotFoundException("Senha não Informada!");
		}
	}

}
