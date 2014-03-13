package br.com.infnet.questionario.daoImp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Session;

import br.com.infnet.questionario.dao.AvaliacaoDAO;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;
import br.com.infnet.questionario.dto.Avaliacao;
import br.com.infnet.questionario.dto.Comentario;
import br.com.infnet.questionario.dto.Questao;
import br.com.infnet.questionario.dto.Resposta;
@Stateless
@Local(AvaliacaoDAO.class)
public class AvaliacaoDaoImp extends GenericCRUD<Avaliacao, Integer> implements AvaliacaoDAO{

	public Avaliacao salvar(Comentario comentario, List<Questao> questoes) throws Exception{
		Session session = (Session) getEntityManager().getDelegate();
		session.beginTransaction();
		
		Avaliacao avaliacao = new Avaliacao();
		List<Comentario> comentarios = new ArrayList<Comentario>();
		List<Resposta> respostas = new ArrayList<Resposta>();
		for(Questao questao : questoes){
			Resposta resposta = new Resposta();
			resposta.setQuestao(questao);
			resposta.setAvaliacao(avaliacao);
			respostas.add(resposta);
		}
		comentario.setAvaliacao(avaliacao);
		comentarios.add(comentario);
		avaliacao.setComentarios(comentarios);
		avaliacao.setRespostas(respostas);
		
		avaliacao.setCodAvaliacao((Integer) session.save(avaliacao));
		session.getTransaction().commit();
		return avaliacao;
	}

}
