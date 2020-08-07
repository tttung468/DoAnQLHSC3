package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.model.HRStaffModel;
import com.toanhuuvuong.pagination.Pageable;

public interface IHRStaffDAO
{
	public HRStaffModel findOne(Long id);
	public HRStaffModel findByCode(String code);
	public HRStaffModel findByAccountId(Long accountId);
	public HRStaffModel findByAccountUsername(String username);
	public List<HRStaffModel> findAll(); 
	public void update(HRStaffModel model);
	public void delete(Long id);
	public Long insertOne(HRStaffModel model);
	public List<HRStaffModel> find(Pageable<HRStaffModel> pageable);
	public Integer count(Pageable<HRStaffModel> pageable);
}