package com.toanhuuvuong.dao.impl;

import java.util.List;

import com.toanhuuvuong.dao.IHRStaffDAO;
import com.toanhuuvuong.model.HRStaffModel;
import com.toanhuuvuong.pagination.Pageable;

public class HRStaffDAO extends GenericDAO<HRStaffModel> implements IHRStaffDAO 
{

	@Override
	public HRStaffModel findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HRStaffModel findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HRStaffModel findByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HRStaffModel findByAccountUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HRStaffModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(HRStaffModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long insertOne(HRStaffModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HRStaffModel> find(Pageable<HRStaffModel> pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer count(Pageable<HRStaffModel> pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
