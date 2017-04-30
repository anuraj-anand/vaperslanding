package com.orderbid.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

public abstract class BaseDaoImpl {

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	public BaseDaoImpl() {
		
	}
	
	public Object createEntity(Object obj){
		return hibernateTemplate.save(obj);
	}

	public void updateEntity(Object obj){
		hibernateTemplate.saveOrUpdate(hibernateTemplate.merge(obj));
	}
	
	public void deleteEntity(Object obj){
		hibernateTemplate.delete(obj);
	}
	
	public void deleteAllEntities(Collection<Object> lstObj){
		hibernateTemplate.deleteAll(lstObj);
	}
	
	public List<?> find(String query){
		return (List<?>) hibernateTemplate.find(query);
	}
	
	public List<?> find(String query, Object[] queryParam){
		return (List<?>) hibernateTemplate.find(query, queryParam);
	}
	
	public Object find(Object obj){
		return hibernateTemplate.findByExample(obj);
	}
	
	@SuppressWarnings("unchecked")
	public Object getEntity(Class entityClass, Integer id){
		return hibernateTemplate.get(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getSQLQueryResult(String sql){
		System.err.println("SQL : "+sql);
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public Query createSQLQuery(String sql){
		System.err.println("SQL : "+sql);
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		return query;
	}
}
