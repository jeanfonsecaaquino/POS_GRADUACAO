package br.com.infnet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.AvaliacaoDAO;
import br.com.infnet.questionario.dao.EventoDAO;
import br.com.infnet.questionario.dao.QuestaoDAO;
import br.com.infnet.questionario.dto.Comentario;
import br.com.infnet.questionario.dto.Modulo;
import br.com.infnet.questionario.dto.OPCAO;
import br.com.infnet.questionario.dto.Questao;

@ManagedBean(name = "MBCadastrarAvaliacao")
@ViewScoped
public class CadastrarAvaliacao implements Serializable {

	private static Logger log = LoggerFactory.getLogger(CadastrarAvaliacao.class);
	private static final long serialVersionUID = 1L;

	private Modulo modulo;
	private List<SelectItem> opcoes;

	@Inject
	private Comentario comentario;

	private List<Questao> questoes;

	@EJB
	private QuestaoDAO questaoDao;

	@EJB
	private AvaliacaoDAO avaliacaoDAO;
	
	@PostConstruct
	public void init() {
		try {
			questoes = questaoDao.listar();
			opcoes = new ArrayList<SelectItem>();
			opcoes.clear();
			for (OPCAO opcao : OPCAO.values()) {
				SelectItem selectItem = new SelectItem(opcao,
						opcao.getStringValue());
				opcoes.add(selectItem);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public Modulo getModulo() {
		if (this.modulo == null) {
			this.modulo = (Modulo) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("modulo");
		}
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<SelectItem> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<SelectItem> opcoes) {
		this.opcoes = opcoes;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public String redirectAvaliar(Modulo modulo) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("modulo", modulo);
		return "avaliar.xhtml?faces-redirect=true";
	}

	public void cadastrarAvaliacao() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			avaliacaoDAO.salvar(comentario, questoes);
			facesContext.addMessage("formCadastroAvaliacao", new FacesMessage(
					FacesMessage.SEVERITY_INFO, null,
					"Avaliação Cadastrada com Sucesso"));
		} catch (Exception e) {
			facesContext.addMessage("formCadastroAvaliacao", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			log.error(e.getMessage(), e);
		}
	}

}