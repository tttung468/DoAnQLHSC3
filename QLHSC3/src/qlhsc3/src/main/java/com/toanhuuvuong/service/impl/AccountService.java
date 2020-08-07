package com.toanhuuvuong.service.impl;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.dao.IAccountDAO;
import com.toanhuuvuong.dao.IRoleDAO;
import com.toanhuuvuong.dao.IVerificationTokenDAO;
import com.toanhuuvuong.dao.impl.AccountDAO;
import com.toanhuuvuong.dao.impl.RoleDAO;
import com.toanhuuvuong.dao.impl.VerificationTokenDAO;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.VerificationTokenModel;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IAccountService;

public class AccountService extends GenericService<AccountModel> implements IAccountService
{ 
	private IAccountDAO accountDAO = new AccountDAO();
	
	private IVerificationTokenDAO verificationTokenDAO = new VerificationTokenDAO();
	
	private IRoleDAO roleDAO = new RoleDAO();
	
	@Override
	protected AccountModel complete(AccountModel model)
	{
		if(model != null)
			model.setRole(roleDAO.findOne(model.getRoleId()));
		return model;
	}
	@Override
	public AccountModel findOne(Long id) 
	{
		return complete(accountDAO.findOne(id));
	}
	@Override
	public AccountModel findByUsername(String username)
	{
		return complete(accountDAO.findByUsername(username));
	}
	@Override
	public List<AccountModel> findByRoleId(Long roleId)
	{
		return complete(accountDAO.findByRoleId(roleId));
	}
	@Override
	public List<AccountModel> findByRoleCode(String roleCode)
	{
		return complete(accountDAO.findByRoleCode(roleCode));
	}
	@Override
	public List<AccountModel> findAll()
	{
		return complete(accountDAO.findAll());
	}
	@Override
	public AccountModel updateOne(AccountModel model, AccountModel editor) 
	{
		AccountModel oldModel = accountDAO.findOne(model.getId());
		model.setCreatedDate(oldModel.getCreatedDate());
		model.setCreatedBy(oldModel.getCreatedBy());
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy((editor != null ? editor.getUsername() : SystemConstant.UNKNOWN));
		
		accountDAO.update(model);
		
		return model;
	}
	@Override
	public void delete(Long[] deletedIds) 
	{
		List<VerificationTokenModel> tokens;
		for(Long id : deletedIds)
		{
			tokens = verificationTokenDAO.findByAccountId(id);
			for(VerificationTokenModel token : tokens)
				verificationTokenDAO.delete(token.getId());
			accountDAO.delete(id);
		}
	}
	@Override
	public AccountModel insertOne(AccountModel model, AccountModel creator) 
	{
		model.setIsDeleted(false);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setCreatedBy((creator != null) ? creator.getUsername() : SystemConstant.UNKNOWN);
		
		Long id = accountDAO.insertOne(model);
		model.setId(id);
		
		return model;
	}
	@Override
	public List<AccountModel> find(Pageable<AccountModel> pageable) 
	{
		return complete(accountDAO.find(pageable));
	}
	@Override
	public Integer count(Pageable<AccountModel> pageable) 
	{
		return accountDAO.count(pageable);
	}
	@Override
	public Map<String, String> validate(AccountModel model) 
	{	
		Map<String, String> map = new Hashtable<String, String>();
		
		if(accountDAO.findByUsername(model.getUsername()) != null)
		{
			map.put("messageCode", "user_existed");
			map.put("alert", "danger");
		}
		else
		{
			map.put("messageCode", "insert_success");
			map.put("alert", "success");
		}
	
		return map;
	}
	@Override
	public boolean isUserRole(AccountModel model) 
	{
		return model.getRole().getCode().toLowerCase().contains(SystemConstant.USER);
	}
}
