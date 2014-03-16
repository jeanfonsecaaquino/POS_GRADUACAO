package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.UsuarioDAO;
import br.com.infnet.questionario.dto.Login;
import br.com.infnet.questionario.dto.PERFIL;
import br.com.infnet.questionario.dto.Usuario;
import br.com.infnet.utils.QuestaoUtils;

@ManagedBean(name = "MBCadastrarUsuario")
@RequestScoped
public class CadastrarUsuario implements Serializable{
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private Login login;
	
	@Inject
	private QuestaoUtils questaoUtils;
	
	private List<SelectItem> perfis;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CadastrarUsuario.class);
	
	@PostConstruct
	public void init(){
		usuario.setLogin(login);
		perfis = new ArrayList<SelectItem>();
		perfis.clear();
		for (PERFIL perfil : PERFIL.values()) {
			SelectItem selectItem = new SelectItem(perfil,perfil.getLabelValue());
			perfis.add(selectItem);
		}
	}
	

	public void cadastrarUsuario(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			usuario.getLogin().setSenha(questaoUtils.encrypt(usuario.getLogin().getSenha()));
			login.setUsuario(usuario);
			usuarioDAO.salvar(usuario);
			facesContext.addMessage("formCadastroUsuario", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Usuário Cadastrado com Sucesso"));
		} catch (Exception e) {
			facesContext.addMessage("formCadastroUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			log.error(e.getMessage(),e);
		}
	}
	
	public QuestaoUtils getQuestaoUtils() {
		return questaoUtils;
	}
	
	public void setQuestaoUtils(QuestaoUtils questaoUtils) {
		this.questaoUtils = questaoUtils;
	}

	public List<SelectItem> getPerfis() {
		return perfis;
	}
	
	public void setPerfis(List<SelectItem> perfis) {
		this.perfis = perfis;
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
