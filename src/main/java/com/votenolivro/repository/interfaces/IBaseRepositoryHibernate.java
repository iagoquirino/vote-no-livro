package com.votenolivro.repository.interfaces;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

public interface IBaseRepositoryHibernate<T> {
	
	T findOneByCriterion( Criterion... criterion );
	
	List<T> findListByCriterion( Criterion... criterion );
	
	T findByOneCriteria( DetachedCriteria criteria );
	
	List<T> findByCriteria( DetachedCriteria criteria );
	
	void delete (T entity);
	
	void deleteAll (List<T> listEntities);
	
	T save(T t);
	
	T saveOrUpdate(T t);
	
	T merge(T t);
	
	List<T> saveOrUpdateAll(List<T> listT);
	
	T loadById( Long id );
	
	List<T> listAll( );
	
	int executeUpdate(String sql);

}
