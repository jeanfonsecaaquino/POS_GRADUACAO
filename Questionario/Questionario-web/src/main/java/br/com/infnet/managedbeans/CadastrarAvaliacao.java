package br.com.infnet.managedbeans;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.com.infnet.questionario.dto.Modulo;

@Named("MBCadastrarAvaliacao")
@ConversationScoped
public class CadastrarAvaliacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private Modulo modulo;

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public String redirectAvaliar(){
		return "avaliar.xhtml";
	}
	
}
