package com.toanhuuvuong.service;

import com.toanhuuvuong.model.HRStaff;

public interface IHRStaffService 
{
	public HRStaff findByCode(String code);
	public HRStaff findByAccountUsername(String username);
}
