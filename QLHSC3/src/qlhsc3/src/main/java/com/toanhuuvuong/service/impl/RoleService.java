package com.toanhuuvuong.service.impl;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.dao.IAccountDAO;
import com.toanhuuvuong.dao.IRoleDAO;
import com.toanhuuvuong.dao.impl.AccountDAO;
import com.toanhuuvuong.dao.impl.RoleDAO;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.RoleModel;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IRoleService;

public class RoleService implements IRoleService
{ 
	private IRoleDAO roleDAO = new RoleDAO();
	 
	private IAccountDAO accountDAO = new AccountDAO();
	
	@Override
	public RoleModel findOne(Long id) 
	{
		return roleDAO.findOne(id);
	}
	@Override
	public RoleModel findByCode(String code) 
	{
		return roleDAO.findByCode(code);
	}
	@Override
	public List<RoleModel> findByLowerPriority(Integer priority)
	{
		return roleDAO.findByLowerPriority(priority);
	}
	@Override
	public List<RoleModel> findAll()
	{
		return roleDAO.findAll();
	}
	@Override
	public RoleModel updateOne(RoleModel model, AccountModel editor) 
	{
		RoleModel oldModel = roleDAO.findOne(model.getId());
		model.setCreatedDate(oldModel.getCreatedDate());
		model.setCreatedBy(oldModel.getCreatedBy());
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy((editor != null ? editor.getUsername() : SystemConstant.UNKNOWN));
		
		roleDAO.update(model);
		
		return model;
	}
	@Override
	public void delete(Long[] deletedIds) 
	{
		List<AccountModel> accounts = null;
		for(Long id : deletedIds)
		{
			accounts = accountDAO.findByRoleId(id);
			for(AccountModel account : accounts)
				accountDAO.delete(account.getId());
			roleDAO.delete(id);
		}
	}
	@Override
	public RoleModel insertOne(RoleModel model, AccountModel creator) 
	{
		model.setIsDeleted(false);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setCreatedBy((creator != null ? creator.getUsername() : SystemConstant.UNKNOWN));
		
		Long id = roleDAO.insertOne(model);
		model.setId(id);
		
		return model;
	}
	@Override
	public List<RoleModel> find(Pageable<RoleModel> pageable) 
	{
		return roleDAO.find(pageable);
	}
	@Override
	public Integer count(Pageable<RoleModel> pageable) 
	{
		return roleDAO.count(pageable);
	}
	@Override
	public Map<String, String> validate(RoleModel model)
	{
		Map<String, String> map = new Hashtable<String, String>();
		
		if(roleDAO.findByCode(model.getCode()) != null)
		{
			map.put("messageCode", "code_existed");
			map.put("alert", "danger");
		}
		else
		{
			map.put("messageCode", "insert_success");
			map.put("alert", "success");
		}
	
		return map;
	}
}
