package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.HRStaff;

public interface IHRStaffDAO
{
	public HRStaff findByCode(String code);
	public HRStaff findByAccountUsername(String username);
}