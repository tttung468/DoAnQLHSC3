package com.toanhuuvuong.utils;

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class HibernateUtil
{
	private static final SessionFactory sessionFactory;

	static 
	{
		try 
		{
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} 
		catch (HibernateException ex) 
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}
	public static void close() throws HibernateException 
	{
		sessionFactory.close();
	}
}
