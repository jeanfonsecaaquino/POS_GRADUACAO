package br.com.infnet.questionario.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cod_login")
	private Integer codLogin;
	
	@Column
	private String login;
	
	@Column
	private String senha;
	
	@Column(columnDefinition="enum('P','A','S')")
	@Enumerated(EnumType.STRING)
	private PERFIL perfil;
	
	@OneToOne
	@JoinColumn(name="cod_usuario")
	private Usuario usuario;
	
	public Login() {
	}

	public Login(Integer codLogin, String login, String senha, PERFIL perfil) {
		this.codLogin = codLogin;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Integer getCodLogin() {
		return codLogin;
	}

	public void setCodLogin(Integer codLogin) {
		this.codLogin = codLogin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PERFIL getPerfil() {
		return perfil;
	}

	public void setPerfil(PERFIL perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codLogin == null) ? 0 : codLogin.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (codLogin == null) {
			if (other.codLogin != null)
				return false;
		} else if (!codLogin.equals(other.codLogin))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (perfil != other.perfil)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [codLogin=" + codLogin + ", login=" + login + ", senha="+ senha + ", perfil=" + perfil + "]";
	}
	
}
