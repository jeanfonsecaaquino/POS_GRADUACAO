package br.com.infnet.mail;

import java.util.Date;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailBean {
	public static final String SERVIDOR_SMTP = "smtp.gmail.com.br";
	public static final int PORTA_SERVIDOR_SMTP = 25;
	private static final String CONTA_PADRAO = "avaliacao.infnet@gmail.com";
	private static final String SENHA_CONTA_PADRAO = "infn&t123";
	
	private String de;
	private String para;
	private String assunto;
	private String mensagem;
	
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
	
	public void enviarEmail(){
		FacesContext context = FacesContext.getCurrentInstance();
		AutenticaUsuario autenticaUsaurio = new AutenticaUsuario(GmailBean.CONTA_PADRAO, GmailBean.SENHA_CONTA_PADRAO);
		Session session = Session.getDefaultInstance(this.configuracaoEmail(), autenticaUsaurio);
		session.setDebug(true);
		
		try{
			Transport envio = null;
			MimeMessage email = new MimeMessage(session);
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(this.para));
			email.setFrom(new InternetAddress(this.de));
			email.setSubject(this.assunto);
			email.setContent(this.mensagem, "text/plain");
			email.setSentDate(new Date());
			envio = session.getTransport();
			envio.connect(GmailBean.SERVIDOR_SMTP, GmailBean.CONTA_PADRAO, GmailBean.SENHA_CONTA_PADRAO);
			email.saveChanges();
			envio.sendMessage(email, email.getAllRecipients());
			envio.close();
			
			context.addMessage(null, new FacesMessage("Email enviado com sucesso"));
			
		} catch(AddressException e){
			FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
			
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}
		
	}
	
	private Properties configuracaoEmail() {
		Properties config = new Properties();
		/*Proxy
		 * 
		 * 
		 * 
		Properties props = new Properties();
		props.setProperty("proxySet", "true");
		props.setProperty("socksProxyHost", "127.0.0.1");
		props.setProperty("socksProxyPort", "8080");
		 * 
		 * 
		 * */
		
		config.put("mail.transport.protocol", "smtp");
		config.put("mail.smtp.starttls.enable", "true");
		config.put("mail.smtp.host", SERVIDOR_SMTP);
		config.put("mail.smtp.auth", "true");
		config.put("mail.smtp.user", GmailBean.CONTA_PADRAO);
		config.put("mail.debug", "true");
		config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
		config.put("mail.smtp.socketFactory.port", PORTA_SERVIDOR_SMTP);
		config.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		config.put("mail.smtp.socketFactory.fallback", "false");
		return config;
	}
	
}
