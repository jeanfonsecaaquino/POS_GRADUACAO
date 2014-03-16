package br.com.infnet.managedbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.dao.QuestaoDAO;
import br.com.infnet.questionario.dto.Questao;

@ManagedBean(name="MBCadastrarQuestao")
@RequestScoped
public class CadastrarQuestao {

	@Inject
	private Questao questao;
	
	@EJB
	private QuestaoDAO questaoDAO;
	
	private static Logger log = LoggerFactory.getLogger(CadastrarQuestao.class);
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public void cadastrarQuestao(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			facesContext.addMessage("formCadastroQuestao", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Questão Cadastrada com Sucesso"));
			questaoDAO.salvar(questao);
		} catch (Exception e) {
			facesContext.addMessage("formCadastroQuestao", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ocorreu um Problema"));
			log.error(e.getMessage(),e);
		}
	}
	
}
