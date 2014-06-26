package br.com.infnet.mail;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

@ManagedBean(name= "commonsMailBean")
@RequestScoped
public class CommonsMailBean {
	
	public static final String SERVIDOR_SMTP = "localhost";
	public static final int PORTA_SERVIDOR_SMTP = 25;
	private static final String CONTA_PADRAO = "avaliacao@localhost";
	private static final String SENHA_CONTA_PADRAO = "123";
	
	private String de;
	private String para;
	private String destinatariosNormais;
	private String destinaratiosOcultos;
	private String assunto;
	private String mensagem;
	private String anexo;
	
	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getDestinatariosNormais() {
		return destinatariosNormais;
	}

	public void setDestinatariosNormais(String destinatariosNormais) {
		this.destinatariosNormais = destinatariosNormais;
	}

	public String getDestinaratiosOcultos() {
		return destinaratiosOcultos;
	}

	public void setDestinaratiosOcultos(String destinaratiosOcultos) {
		this.destinaratiosOcultos = destinaratiosOcultos;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public void enviarAutenticado(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(CommonsMailBean.SERVIDOR_SMTP);
			email.setSmtpPort(CommonsMailBean.PORTA_SERVIDOR_SMTP);
			email.setAuthentication(CommonsMailBean.CONTA_PADRAO, CommonsMailBean.SENHA_CONTA_PADRAO);
			email.setFrom(this.de, "Infnet");
			email.setSubject(this.assunto);
			email.setMsg(this.mensagem);
			List<InternetAddress> normais = this.montaDestinatarios(this.destinatariosNormais);
			if(normais!=null){
				email.setCc(normais);
			}
			List<InternetAddress> ocultos = this.montaDestinatarios(this.destinatariosNormais);
			if(ocultos!=null){
				email.setBcc(ocultos);
			}
			if(this.anexo != null && this.anexo.trim().length()>0){
				EmailAttachment anexo = new EmailAttachment();
				anexo.setPath(this.anexo);
				anexo.setDisposition(EmailAttachment.ATTACHMENT);
				email.attach(anexo);
			}
			email.send();
			context.addMessage(null, new FacesMessage("Email enviado com sucesso"));
			
		}catch(EmailException e){
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}catch(AddressException e){
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}
	}

	public void enviarSimples(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			SimpleEmail email = new SimpleEmail();
			email.setHostName(CommonsMailBean.SERVIDOR_SMTP);
			email.setSmtpPort(CommonsMailBean.PORTA_SERVIDOR_SMTP);
			email.setAuthentication(CommonsMailBean.CONTA_PADRAO, CommonsMailBean.SENHA_CONTA_PADRAO);
			email.setFrom(this.de, "Infnet");
			email.setSubject(this.assunto);
			email.setMsg(this.mensagem);
			List<InternetAddress> normais = this.montaDestinatarios(this.destinatariosNormais);
			if(normais!=null){
				email.setCc(normais);
			}
			email.send();
			context.addMessage(null, new FacesMessage("Email enviado com sucesso"));
		}catch(EmailException e){
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}catch(AddressException e){
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}
	}

	private List<InternetAddress> montaDestinatarios(String destinatarios) throws AddressException {
		List<InternetAddress> emails = null;
		if(destinatarios !=null && destinatarios.trim().length()>0){
			String[]lista = destinatarios.split(";");
			emails = new ArrayList<InternetAddress>();
			for(int i = 0; i < lista.length; i++){
				emails.add(new InternetAddress(lista[i]));
			}
		}
		return emails;
	}
}
