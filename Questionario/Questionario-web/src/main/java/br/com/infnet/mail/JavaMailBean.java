package br.com.infnet.mail;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@ManagedBean(name = "javaMailBean")
@RequestScoped
public class JavaMailBean {

	public static final String 	SERVIDOR_SMTP = "localhost";
	public static final int 		PORTA_SERVIDOR_SMTP = 25;
	//Conta a ser configurada a enviar o email
	private static final String 	CONTA_PADRAO = "postmaster@localhost";
	private static final String 	SENHA_CONTA_PADRAO = "123";

	private String 				de;
	private String 				para;
	private String 				destinatariosNormais;
	private String 				destinatariosOcultos;
	private String 				assunto;
	private String 				mensagem;
	private String 				anexo;

	public void enviarAutenticado() {
		FacesContext context = FacesContext.getCurrentInstance();

		Properties config = new Properties(); 
		config.put("mail.smtp.host", JavaMailBean.SERVIDOR_SMTP);
		config.put("mail.smtp.port", JavaMailBean.PORTA_SERVIDOR_SMTP);
		config.put("mail.smtp.auth", "true");

		Session sessao = Session.getInstance(config, new AutenticaUsuario(JavaMailBean.CONTA_PADRAO, JavaMailBean.SENHA_CONTA_PADRAO)); 

		try {
			MimeMessage email = new MimeMessage(sessao); 
			email.setFrom(new InternetAddress(this.de));
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(this.para));

			InternetAddress[] normais = this.montaDestinatarios(this.destinatariosNormais);
			if (normais != null) {
				email.setRecipients(Message.RecipientType.CC, normais);
			}
			
			InternetAddress[] ocultos = this.montaDestinatarios(this.destinatariosOcultos);
			if (ocultos != null) {
				email.setRecipients(Message.RecipientType.BCC, ocultos);
			}

			email.setSubject(this.assunto);
			email.setSentDate(new Date());

			// Preparando o corpo de email
			MimeMultipart partesEmail = new MimeMultipart(); 
			MimeBodyPart corpoEmail = new MimeBodyPart();
			corpoEmail.setContent(this.mensagem, "text/html");
			partesEmail.addBodyPart(corpoEmail);

			// Anexando arquivo
			if (this.anexo != null && this.anexo.trim().length() > 0) {
				MimeBodyPart anexo = new MimeBodyPart();
				FileDataSource arquivo = new FileDataSource(this.anexo); 
				anexo.setDataHandler(new DataHandler(arquivo));
				anexo.setFileName(arquivo.getName());
				partesEmail.addBodyPart(anexo);
			} 

			email.setContent(partesEmail);

			Transport.send(email); 

			context.addMessage(null, new FacesMessage("E-mail enviado com sucesso"));

		} catch (AddressException e) {
			FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}
	}

	public void enviarSimples() { 
		FacesContext context = FacesContext.getCurrentInstance();

		Properties config = new Properties(); 
		config.put("mail.smtp.host", JavaMailBean.SERVIDOR_SMTP);
		config.put("mail.smtp.port", JavaMailBean.PORTA_SERVIDOR_SMTP);

		Session sessao = Session.getInstance(config);

		try {
			MimeMessage email = new MimeMessage(sessao);
			email.setFrom(new InternetAddress(this.de));
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(this.para));

			InternetAddress[] normais = this.montaDestinatarios(this.destinatariosNormais);
			if (normais != null) {
				email.setRecipients(Message.RecipientType.CC, normais);
			}
			
			InternetAddress[] ocultos = this.montaDestinatarios(this.destinatariosOcultos);
			if (normais != null) {
				email.setRecipients(Message.RecipientType.BCC, ocultos);
			}

			email.setSubject(this.assunto);
			email.setSentDate(new Date());
			email.setText(this.mensagem);
			Transport.send(email);

			context.addMessage(null, new FacesMessage("E-mail enviado com sucesso"));

		} catch (AddressException e) {
			FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}
	}

	private InternetAddress[] montaDestinatarios(String destinatarios) throws AddressException { 
		InternetAddress[] emails = null;
		if (destinatarios != null && destinatarios.trim().length() > 0) {			
			String[] lista = destinatarios.split(";");
			emails = new InternetAddress[lista.length];
			for (int i = 0; i < lista.length; i++) {
				emails[i] = new InternetAddress(lista[i]);
			}
		}
		return emails;
	}

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

	public String getDestinatariosOcultos() {
		return destinatariosOcultos;
	}

	public void setDestinatariosOcultos(String destinatariosOcultos) {
		this.destinatariosOcultos = destinatariosOcultos;
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
}
