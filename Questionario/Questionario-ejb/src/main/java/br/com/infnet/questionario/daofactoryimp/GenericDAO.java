package br.com.infnet.questionario.daofactoryimp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Criterion;


public interface GenericDAO<T, ID extends Serializable> {
	
	 EntityManager getEntityManager();

	 void setEntityManager(EntityManager entityManager);

	 List<T> buscarPorCriteria(Criterion... criterions) throws Exception ;

	 void excluir(T entity) throws Exception ;

	 List<T> listar() throws Exception ;

	 T buscarPorId(ID id)throws Exception ;

	 T salvar(T entity)throws Exception ;

	 T alterar(T entity)throws Exception ;

	 T mesclar(T entity)throws Exception ;

	 T salvarOuAlterar(T entity)throws Exception ;
	
	 void destacar(T entity) throws Exception ;
	
	 Long retornarMaxId() throws Exception;
}
