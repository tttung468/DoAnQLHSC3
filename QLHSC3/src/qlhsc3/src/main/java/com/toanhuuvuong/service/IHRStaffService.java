package com.toanhuuvuong.service;

import java.util.List;
import java.util.Map;

import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.HRStaffModel;
import com.toanhuuvuong.pagination.Pageable;

public interface IHRStaffService 
{
	public HRStaffModel findOne(Long id);
	public HRStaffModel findByCode(String code);
	public HRStaffModel findByAccountId(Long accountId);
	public HRStaffModel findByAccountUsername(String username);
	public List<HRStaffModel> findAll();
	public HRStaffModel updateOne(HRStaffModel model, AccountModel editor);
	public void delete(Long[] deletedIds);
	public HRStaffModel insertOne(HRStaffModel model, AccountModel creator);
	public List<HRStaffModel> find(Pageable<HRStaffModel> pageable);
	public Integer count(Pageable<HRStaffModel> pageable);
	public Map<String, String> validate(HRStaffModel model);
}
