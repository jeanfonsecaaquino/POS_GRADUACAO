package br.edu.infnet.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticaUsuario extends Authenticator {

	private String usuario;
	private String senha;
	
	public AutenticaUsuario(String usuario, String senha){
		this.usuario = usuario;
		this.senha = senha;
	}
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(this.usuario, this.senha);
	}
}
