package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.pagination.Pageable;

public interface IGenericDAO<T>
{
	public T findOne(Long id);
	public List<T> findAll();
	public Long insertOne(T entity);
	public void updateOne(T entity);
	public void deleteOne(T entity);
	public void deleteById(Long id);
	public List<T> find(Pageable<T> pageable);
	public Long count(Pageable<T> pageable);
}
