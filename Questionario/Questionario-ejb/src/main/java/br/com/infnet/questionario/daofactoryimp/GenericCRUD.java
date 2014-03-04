package br.com.infnet.questionario.daofactoryimp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public abstract class GenericCRUD<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private static Logger log = LoggerFactory.getLogger(GenericCRUD.class);
	
	@PersistenceContext(unitName="questionario")
	private EntityManager entityManager;
	
	private Class<T> classePersistente;

	public void destacar(T entity)throws Exception{
		((Session) getEntityManager().getDelegate()).evict(entity);
	}
	
	/**
	 * No construtor foi usado Reflection para fazer o casting correto para o tipo
	 * que realmente foi instanciado.
	 * 
	 */
	public GenericCRUD() {
		this.classePersistente = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	

	/**
	 * 
	 * Obtem a implementação dessa classe, ou seja, a instância da sub-classe
	 * 
	 * @return
	 */
	public Class<T> getClassePersistente() {
		return classePersistente;
	}

	/**
	 * 
	 * Define o valor da classe persistente.
	 * 
	 * @param classePersistente
	 */
	public void setClassePersistente(Class<T> classePersistente) {
		this.classePersistente = classePersistente;
	}

	/**
	 * 
	 * Criação de uma entidade.
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T salvar(T entity)throws Exception {
		
		try {
			
			((Session) getEntityManager().getDelegate()).save(entity);

		} catch (HibernateException e) {
			log.info(e.getMessage(), e.getStackTrace());
		}

		return entity;
	}

	/**
	 * 
	 * Criação de uma entidade no caso de não existir ou alteração no caso de
	 * existir.
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T salvarOuAlterar(T entity)throws Exception{
		
		try {

			((Session) getEntityManager().getDelegate()).saveOrUpdate(entity);

		} catch (HibernateException e) {
			log.info(e.getMessage(), e.getStackTrace());
		}

		return entity;
	}

	/**
	 * exclusão de uma entidade.
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void excluir(T entity)throws Exception {
		
		try {
			
			((Session) getEntityManager().getDelegate()).delete(entity);

		} catch (HibernateException e) {
			log.info(e.getMessage(), e.getStackTrace());
		}
	}

	/**
	 * Retorna uma lista completa da Entidade.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> listar()throws Exception {
		
		List<T> lista = null;
		
		try {
			
			lista = buscarPorCriteria();
			
		} catch (HibernateException e) {
			log.info(e.getMessage(), e.getStackTrace());
		}
		
		
		return lista;
	}

	/**
	 * Retorna uma lista de objetos de acordo com o conjunto de criterios passados .
	 * 
	 * @param criterions
	 * @return
	 * @throws Exception
	 */
	public List<T> buscarPorCriteria(Criterion... criterions) throws Exception{

		Criteria criteria = null;

		try {
			
			criteria = ((Session) getEntityManager().getDelegate()).createCriteria(getClassePersistente());

			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}

		} catch (Exception e) {
			log.info(e.getMessage(), e.getStackTrace());
		}

		return criteria.list();
	}

	/**
	 * Obtem uma entidade pelo seu ID.
	 * Usando o metodo find(); do EntityManager
	 * que retorna um objeto completo e o torna gerenciavel enquanto a sessãoo estiver aberta.
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T buscarPorId(ID id) throws Exception{	
		
		T entidade = null;
		
		try {
			
			entidade = getEntityManager().find(getClassePersistente(), id);
			
		} catch (HibernateException e) {
			log.info(e.getMessage(), e.getStackTrace());
		}
  	
		return entidade;
  	
	}

	/**
	 * alteração de uma entidade.
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T alterar(T entidade) throws Exception{
		
		try {
			
			((Session) getEntityManager().getDelegate()).update(entidade);

		} catch (HibernateException e) {
			log.info(e.getMessage(), e.getStackTrace());
		}
		return entidade;
	}

	/**
	 * Mesclagem (merge) de uma entidade.
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T mesclar(T entidade) throws Exception{
		
		try {
			return getEntityManager().merge(entidade);
		} catch (HibernateException e) {
			log.info(e.getMessage(), e.getStackTrace());
			return null;
		}
	}
	public Long retornarMaxId() throws Exception {
		Session sessao = (Session) getEntityManager().getDelegate();
		Criteria criteria = sessao.createCriteria(getClassePersistente());
		
		criteria.setProjection(Projections.projectionList().add(Projections.max("id")));
		
		Long idMaximo = (Long) criteria.uniqueResult();
		if(idMaximo == null){
			idMaximo = new Long(1);
		}
		
		return idMaximo;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
