package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.infnet.questionario.dao.UsuarioDAO;
import br.com.infnet.questionario.dto.Usuario;

@ManagedBean(name="MBAlterarUsuario")
@ViewScoped
public class AlterarUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioDAO usuarioDAO;
	private List<Usuario> usuarios;
	private Usuario usuario;
	
	@PostConstruct
	public void init(){
	
		
	}

	public List<Usuario> getUsuarios() {
		if(this.usuarios == null){
			try {
				this.setUsuarios(usuarioDAO.listar());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String alterarUsuario(Usuario usuario){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
		return "cadastroNovoUsuario.xhtml";	
	}
	
	public String excluirUsuario() throws Exception{
		usuarioDAO.excluir(usuario);
		this.usuarios = null;
		return null;		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
