package br.com.infnet.questionario.daofactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.infnet.questionario.daofactory.DAOFactory;
import br.com.infnet.questionario.daofactoryimp.GenericCRUD;

@Stateless
public class DAOFactoryImpl implements DAOFactory{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String PACOTE_ENTIDADES = "entidades";
	private static final String PACOTE_CRUD = "jean.archetype.daoFactoryImp";
	private static final String SUFIXO_CRUD = "CRUD";
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public static String getNomeDAO(Class<?> entidade){
		
		 String nome = entidade.getName().replace(PACOTE_ENTIDADES, PACOTE_CRUD) + SUFIXO_CRUD;

		 return nome;
	}

	
	public GenericCRUD<?, ?> getDAO(Class<?> entidade) throws Exception {
		
		GenericCRUD<?,?> crud = null;
		
		String nome = getNomeDAO(entidade);
		
		try{
			crud = (GenericCRUD<?,?>) Class.forName(nome).newInstance();
			crud.setEntityManager(entityManager);
		} catch (InstantiationException e) {
			   log.info(e.getMessage(), e.getStackTrace());
	           throw new Exception(e.getMessage(), e);
	    } catch (IllegalAccessException e) {
	    	   log.info(e.getMessage(), e.getStackTrace());
	           throw new Exception(e.getMessage(), e);
	    } catch (ClassNotFoundException e) {
	    	   log.info(e.getMessage(), e.getStackTrace());
	           throw new Exception(e.getMessage(), e);
	    }

		return crud;
	}

}
