package com.toanhuuvuong.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Generic;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IGenericService;

public abstract class GenericService<T extends Generic & Serializable> implements IGenericService<T>
{
	@Override
	public T findOne(Long id) 
	{
		return getDAO().findOne(id);
	}
	@Override
	public List<T> findAll() 
	{
		return getDAO().findAll();
	}
	@Override
	public T updateOne(T model, Account editor) 
	{
		T oldModel = getDAO().findOne(model.getId());
		model.setCreatedDate(oldModel.getCreatedDate());
		model.setCreatedBy(oldModel.getCreatedBy());
		model.setModifiedDate(new Date(System.currentTimeMillis()));
		model.setModifiedBy((editor != null ? editor.getUsername() : SystemConstant.UNKNOWN));
		
		getDAO().updateOne(model);
		
		return model;
	}
	@Override
	public void delete(Long[] deletedIds) 
	{
		deleteRelative(deletedIds);
		for(Long id : deletedIds)
			getDAO().deleteById(id);
	}
	public void deleteRelative(Long[] deletedIds) { return; }
	@Override
	public T insertOne(T model, Account creator) 
	{
		model.setIsDeleted(false);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setCreatedBy((creator != null ? creator.getUsername() : SystemConstant.UNKNOWN));
		
		Long id = getDAO().insertOne(model);
		model.setId(id);
		
		return model;
	}
	@Override
	public List<T> find(Pageable<T> pageable)
	{
		return getDAO().find(pageable);
	}
	@Override
	public Long count(Pageable<T> pageable) 
	{
		return getDAO().count(pageable);
	}
	abstract public Map<String, String> validate(T model);
	abstract public AbstractHibernateDAO<T> getDAO();
}
