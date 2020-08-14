package com.toanhuuvuong.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.utils.HibernateUtil;

public abstract class AbstractHibernateDAO<T extends Serializable>
{
	private Class<T> clazz;

	public void setClazz(Class<T> clazzToSet) 
	{
		clazz = clazzToSet;
	}
	public T findOne(Long id) 
	{
		T entity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
			entity = (T) session.get(clazz, id);
			tx.commit();
		} 
		catch(Exception e) 
		{
			if (tx != null) 
				tx.rollback();
			
			e.printStackTrace();
		} 
		finally 
		{
			session.close();
		}

		return entity;
	}
	public List<T> findAll()
	{
		List<T> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try
		{
			String hql = "from " + clazz.getName();
			Query query = session.createQuery(hql);

			tx = session.beginTransaction();
			list = query.list(); // get all
			tx.commit();
		} 
		catch(Exception e) 
		{
			if (tx != null)
				tx.rollback();

			e.printStackTrace();
		} 
		finally 
		{
			session.close();
		}

		return list;
	}
	public Long insertOne(T entity) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Long id = -1L;

		try 
		{
			tx = session.beginTransaction();
			id = (long)session.save(entity);
			tx.commit();
		} 
		catch(Exception e) 
		{
			if (tx != null)
				tx.rollback();
			
			e.printStackTrace();
		} 
		finally 
		{
			session.close();
		}

		return id;
	}
	public void updateOne(T entity) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try 
		{
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} 
		catch(Exception e)
		{
			if (tx != null)
				tx.rollback();
			
			e.printStackTrace();
		} 
		finally 
		{
			session.close();
		}
	}
	public void deleteOne(T entity)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try 
		{
			tx = session.beginTransaction();
			session.delete(entity); // delete
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			
			e.printStackTrace();
		} 
		finally 
		{
			session.close();
		}
	}
	public void deleteById(Long id) 
	{
		final T entity = findOne(id);
		deleteOne(entity);
	}
	public List<T> find(Pageable<T> pageable) 
	{
		List<T> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Query query;

		// Tạo câu truy vấn
		String hql = generateHQLForFind(pageable);

		if (hql != null) 
		{
			try 
			{
				// Create query
				query = setValueForHQL(pageable, session, hql);

				// Query
				tx = session.beginTransaction();

				if (query != null)
					list = query.list(); // get all

				tx.commit();
			} 
			catch(Exception e)
			{
				if (tx != null)
					tx.rollback();
			
				e.printStackTrace();
			} 
			finally 
			{
				session.close();
			}
		}

		return list;
	}
	public Long count(Pageable<T> pageable) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Query query;
		Long count = new Long(-1);

		// Tạo câu truy vấn
		String hql = generateHQLForCount(pageable);

		if (hql != null) {
			try {
				// Create query
				query = setValueForHQL(pageable, session, hql);

				// Query
				tx = session.beginTransaction();

				if (query != null) 
				{
					Iterator iterator = query.iterate();
					count = (Long) iterator.next(); // count
				}

				tx.commit();
			} 
			catch (Exception e)
			{
				if (tx != null)
					tx.rollback();
				
				e.printStackTrace();
			} 
			finally 
			{
				session.close();
			}
		}

		return count;
	}
	public String generateHQL(Pageable<T> pageable) 
	{
		return null;
	}
	public String generateHQLForFind(Pageable<T> pageable) 
	{
		return null;
	}
	public String generateHQLForCount(Pageable<T> pageable) 
	{
		return null;
	}
	public Query setValueForHQL(Pageable<T> pageable, Session session, String hql) 
	{
		return null;
	}
}
