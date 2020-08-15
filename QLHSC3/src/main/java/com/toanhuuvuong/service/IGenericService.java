package com.toanhuuvuong.service;

import java.util.List;

import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.pagination.Pageable;

public interface IGenericService<T>
{
	public T findOne(Long id);
	public List<T> findAll();
	public T updateOne(T model, Account editor);
	public void delete(Long[] deletedIds);
	public T insertOne(T model, Account creator);
	public List<T> find(Pageable<T> pageable);
	public Long count(Pageable<T> pageable);
}
