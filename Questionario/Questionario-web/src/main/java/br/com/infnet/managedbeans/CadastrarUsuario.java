package br.com.infnet.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.UsuarioDAO;
import br.com.infnet.questionario.dto.Login;
import br.com.infnet.questionario.dto.Modulo;
import br.com.infnet.questionario.dto.Usuario;

@ManagedBean(name = "MBCadastrarUsuario")
@RequestScoped
//OBS: Na página inclui um cabeçalho de header e uma configuração de css, da uma olhada no avaliar.xhtml, dai ele vai vir com o menu ja macetado, ai vc pode incluir o menu lá
// do cadastro e delegar ele para o perfil que podera cadastrar o usuário.. nem faço ideia do perfil que fará isso,, hehe agente pode discutir depois.. 
public class CadastrarUsuario implements Serializable{
	
	@Inject
	private Usuario usuario;
	@Inject
	private Login login;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CadastrarUsuario.class);
	
	@PostConstruct
	public void init(){
		usuario.setLogin(login);
//		Associei ambos para que na pagina os campos sejam inputados da seguinte forma --> #{MBCadastrarUsuario.usuario.login.senha}, #{MBCadastrarUsuario.usuario.login.login} etc.. 
	}
	
	public void inserirLogin(){
//		Associação inversa para o hibernate gerar as chaves FK's nas Tabelas OBS: Não esquecer de incluir o cascade no mapeamento, da uma olhada como ficou o dto avaliação para ter uma ideia do Cascate.Type.All
		login.setUsuario(usuario);
		try {
			usuarioDAO.salvar(usuario);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	public void cadastrarUsuario(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			usuarioDAO.salvar(usuario,login);
			facesContext.addMessage("formCadastroUsuario", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Usuário Cadastrado com Sucesso"));
		} catch (Exception e) {
			facesContext.addMessage("formCadastroUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			log.error(e.getMessage(),e);
		}
	}
	
	
	public String redirectIndex(){
		return "index.xhtml?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	
}
